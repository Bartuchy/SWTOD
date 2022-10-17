package com.example.swtod.user.dto;

import com.example.swtod.entity.Position;
import com.example.swtod.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UpdateUserDto {
    private String username;
    private String name;
    private String surname;
    private LocalDate dob;
    private String title;
    private Long positionId;

    public static void mapToUser(User user, UpdateUserDto userDto) {
        user.setUsername(userDto.getUsername());
        user.setTitle(userDto.getTitle());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setDob(userDto.getDob());
        user.setPosition(new Position(userDto.getPositionId()));
    }
}
