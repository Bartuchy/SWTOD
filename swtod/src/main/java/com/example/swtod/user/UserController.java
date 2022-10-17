package com.example.swtod.user;

import com.example.swtod.security.jwt.JwtToken;
import com.example.swtod.user.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("api/user")
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final JwtToken jwtToken;
    private final UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        authenticate(loginRequestDto.getUsername(), loginRequestDto.getPassword());

        User user = userService.login(loginRequestDto.getUsername());
        String token = jwtToken.generateToken(user);

        return ResponseEntity.ok(LoginResponseDto.mapToDto(user, token));
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createNewUser(@RequestBody CreateUserDto userDto) {
        userService.createUser(userDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}/update")
    public ResponseEntity<Void> updateUserData(@PathVariable Long id, @RequestBody UpdateUserDto updateUserDto) {
        userService.updateUserData(id, updateUserDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/reset-password/{username}")
    public ResponseEntity<Void> resetPassword(@PathVariable String username) {
        userService.resetPassword(username);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/change-password")
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordDto passwordDto) {
        authenticate(passwordDto.getUsername(), passwordDto.getOldPassword());
        userService.updatePassword(passwordDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/deactivate-account/{id}")
    public ResponseEntity<Void> deactivateAccount(@PathVariable Long id) {
        userService.deactivateAccount(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/activate-account/{id}")
    public ResponseEntity<Void> activateAccount(@PathVariable Long id) {
        userService.activateAccount(id);
        return ResponseEntity.ok().build();
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
