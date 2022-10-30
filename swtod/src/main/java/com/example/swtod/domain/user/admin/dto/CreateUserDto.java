package com.example.swtod.domain.user.admin.dto;

import com.example.swtod.domain.user.User;
import com.example.swtod.domain.entity.Position;
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
    private Long positionId;

    public static User mapToUser(CreateUserDto userDto, String password) {
        return new User(
                userDto.title,
                userDto.username,
                userDto.name,
                userDto.surname,
                userDto.dob,
                password,
                new Position(userDto.positionId)
        );
    }
}
