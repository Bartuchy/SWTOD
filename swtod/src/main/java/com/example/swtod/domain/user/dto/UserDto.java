package com.example.swtod.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String title;
    private String positionName;
    private String dob;
    private Boolean isAdmin;
}
