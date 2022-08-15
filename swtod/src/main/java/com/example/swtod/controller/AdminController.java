package com.example.swtod.controller;

import com.example.swtod.dto.CreateUserDto;
import com.example.swtod.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("api/admin")
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDto userDto) {
        adminService.createUser(userDto);
        return ResponseEntity.ok().build();
    }
}
