package com.example.swtod.domain.teaching.staff;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class PYSUController {
    private final PYSUService pysuService;

    @PostMapping("/assign-group")
    public ResponseEntity<Void> assignGroupToUser(@RequestParam Long userId, @RequestParam Long subjectId) {
        pysuService.assignGroupToUser(userId, subjectId);

        return ResponseEntity.ok().build();
    }
}
