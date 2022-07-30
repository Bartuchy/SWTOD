package com.example.swtod.dto;

import com.example.swtod.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {
    private String username;
    private String password;
    private String token;

    public static LoginResponseDto mapToDto(User user, String token) {
        return new LoginResponseDto(
                user.getUsername(),
                user.getPassword(),
                token
        );
    }
}
