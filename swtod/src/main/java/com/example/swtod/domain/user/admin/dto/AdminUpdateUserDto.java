package com.example.swtod.domain.user.admin.dto;

import com.example.swtod.domain.common.entity.Position;
import com.example.swtod.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminUpdateUserDto {
    private String username;
    private String title;
    private Long positionId;

    public static void mapToUser(User user, AdminUpdateUserDto userDto) {
        user.setUsername(userDto.getUsername());
        user.setTitle(userDto.getTitle());
        user.setPosition(new Position(userDto.getPositionId()));
    }
}
