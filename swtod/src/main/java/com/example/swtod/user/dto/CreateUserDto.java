package com.example.swtod.user.dto;

import com.example.swtod.entity.Position;
import com.example.swtod.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CreateUserDto {
    private String username;
    private String name;
    private String surname;
    private LocalDate dob;
    private String title;
    private boolean is_admin;
    private Position position;

    public static User mapToUser(CreateUserDto userDto, String password) {
        return new User(
                userDto.title,
                userDto.username,
                userDto.name,
                userDto.surname,
                userDto.dob,
                userDto.is_admin,
                password,
                userDto.position
        );
    }
}
