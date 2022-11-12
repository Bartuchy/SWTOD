package com.example.swtod.domain.user.admin;

import com.example.swtod.domain.user.admin.dto.AdminUpdateUserDto;
import com.example.swtod.domain.user.UserService;
import com.example.swtod.domain.user.admin.dto.CreateUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final UserService userService;

    @GetMapping("/is-admin")
    public ResponseEntity<Void> return200kIfIsAdmin() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create-user")
    public ResponseEntity<Void> createNewUser(@RequestBody CreateUserDto userDto) {
        userService.createUser(userDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}/update")
    public ResponseEntity<Void> updateUserData(@PathVariable Long id, @RequestBody AdminUpdateUserDto updateUserDto) {
        userService.updateUserDataByAdmin(id, updateUserDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/deactivate-account")
    public ResponseEntity<Void> deactivateAccount(@PathVariable Long id) {
        userService.deactivateAccount(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/activate-account")
    public ResponseEntity<Void> activateAccount(@PathVariable Long id) {
        userService.activateAccount(id);
        return ResponseEntity.ok().build();
    }
}
