package com.example.swtod.dto;

import com.example.swtod.entity.Position;
import com.example.swtod.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CreateUserDto {
    private String username;
    private String name;
    private String surname;
    private String password;
    private LocalDate dob;
    private String title;
    private Long position_id;

    public static User mapToUserEntity(CreateUserDto userDto) {
        return new User(
                userDto.getUsername(),
                userDto.getName(),
                userDto.getSurname(),
                userDto.getPassword(),
                userDto.getDob(),
                userDto.getTitle(),
                new Position(userDto.getPosition_id())
        );
    }
}
