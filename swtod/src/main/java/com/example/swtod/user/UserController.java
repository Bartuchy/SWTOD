package com.example.swtod.user;

import com.example.swtod.security.jwt.JwtToken;
import com.example.swtod.user.dto.CreateUserDto;
import com.example.swtod.user.dto.LoginRequestDto;
import com.example.swtod.user.dto.LoginResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("api")
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final JwtToken jwtToken;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        System.out.println(loginRequestDto.getPassword());
        authenticate(loginRequestDto.getUsername(), loginRequestDto.getPassword());
        User user = userService.login(loginRequestDto.getUsername());
        String token = jwtToken.generateToken(user);

        return ResponseEntity.ok(LoginResponseDto.mapToDto(user, token));
    }

    @PostMapping("/create-user")
    public ResponseEntity<Void> createNewUser(@RequestBody CreateUserDto userDto) {
        userService.createUser(userDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestParam String username) {
        userService.resetPassword(username);
        return ResponseEntity.ok().build();
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

}
