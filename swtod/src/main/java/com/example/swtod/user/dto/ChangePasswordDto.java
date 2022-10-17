package com.example.swtod.user.dto;

import lombok.Data;

@Data
public class ChangePasswordDto {
    private String username;
    private String oldPassword;
    private String newPassword;
    private String repeatedNewPassword;
}
