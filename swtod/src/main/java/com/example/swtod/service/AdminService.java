package com.example.swtod.service;

import com.example.swtod.dto.CreateUserDto;
import com.example.swtod.entity.User;
import com.example.swtod.repository.AdminRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepo adminRepo;

    public void createUser(CreateUserDto userDto) {
        //TODO check if user exists
        User user = CreateUserDto.mapToUserEntity(userDto);
        adminRepo.save(user);
    }

//    public User findByUsername(String username) {
//        return
//    }
}
