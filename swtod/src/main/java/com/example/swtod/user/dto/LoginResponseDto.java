package com.example.swtod.user.dto;

import com.example.swtod.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {
    private String username;
    private String token;

    public static LoginResponseDto mapToDto(User user, String token) {
        return new LoginResponseDto(
                user.getUsername(),
                token
        );
    }
}
