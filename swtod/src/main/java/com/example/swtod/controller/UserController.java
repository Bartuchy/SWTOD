package com.example.swtod.controller;

import com.example.swtod.security.jwt.JwtToken;
import com.example.swtod.dto.LoginRequestDto;
import com.example.swtod.dto.LoginResponseDto;
import com.example.swtod.entity.User;
import com.example.swtod.security.UserDetailsServiceImpl;
import com.example.swtod.service.UserService;
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
    private final UserDetailsServiceImpl detailsService;
    private final JwtToken jwtToken;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        authenticate(loginRequestDto.getUsername(), loginRequestDto.getPassword());
        User user = userService.login(loginRequestDto.getUsername());
        String token = jwtToken.generateToken(user);

        return ResponseEntity.ok(LoginResponseDto.mapToDto(user, token));
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addNewUser(@RequestBody User user) {
        userService.add(user);
        return ResponseEntity.ok().build();
    }
}
