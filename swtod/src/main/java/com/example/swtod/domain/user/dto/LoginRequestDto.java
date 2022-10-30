package com.example.swtod.domain.user.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String username;
    private String password;
}
