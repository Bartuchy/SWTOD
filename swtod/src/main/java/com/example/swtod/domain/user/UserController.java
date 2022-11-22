package com.example.swtod.domain.user;

import com.example.swtod.domain.user.dto.*;
import com.example.swtod.security.jwt.JwtToken;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final JwtToken jwtToken;
    private final UserService userService;

    @GetMapping("/is-user")
    public ResponseEntity<Void> return200kIfIsUser() {
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        authenticate(loginRequestDto.getUsername(), loginRequestDto.getPassword());

        User user = userService.getLoggedInUser(loginRequestDto.getUsername());
        String token = jwtToken.generateToken(user);

        return ResponseEntity.ok(LoginResponseDto.mapToDto(user, token));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtos = userService.getAllUsers();
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping
    public ResponseEntity<UserDto> getUserByUsername(@RequestParam String username) {
        UserDto userDto = userService.getUserByUsername(username);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("{id}/update")
    public ResponseEntity<Void> updateUserData(@PathVariable Long id, @RequestBody UpdateUserDto updateUserDto) {
        userService.updateUserDataByUser(id, updateUserDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestParam String username) {
        userService.resetPassword(username);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/change-password")
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordDto passwordDto) {
        authenticate(passwordDto.getUsername(), passwordDto.getOldPassword());
        userService.updatePassword(passwordDto);
        return ResponseEntity.ok().build();
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
