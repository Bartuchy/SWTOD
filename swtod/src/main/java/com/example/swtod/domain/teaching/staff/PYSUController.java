package com.example.swtod.domain.teaching.staff;

import com.example.swtod.domain.teaching.staff.dto.AssignedGroupsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/plan-year-subject-user")
public class PYSUController {
    private final PYSUService pysuService;

    @PostMapping("/assign-groups")
    public ResponseEntity<Void> assignGroupToUser(
            @RequestParam Long userId,
            @RequestParam Long subjectId,
            @RequestBody AssignedGroupsDto groupsDto) {
        pysuService.assignGroupToUser(userId, subjectId, groupsDto);

        return ResponseEntity.ok().build();
    }
}
