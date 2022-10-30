package com.example.swtod.domain.user.dto;

import com.example.swtod.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UpdateUserDto {
    private String name;
    private String surname;
    private LocalDate dob;

    public static void mapToUser(User user, UpdateUserDto userDto) {
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setDob(userDto.getDob());
    }
}
