package com.example.swtod.domain.teaching.staff;

import com.example.swtod.domain.teaching.staff.dto.AssignedGroupsDto;
import com.example.swtod.domain.teaching.staff.dto.PYSURecordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
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

    @GetMapping("/")
    public ResponseEntity<List<PYSURecordDto>> getTeachingStaff() {
        List<PYSURecordDto> pysuRecordDtos = pysuService.getTeachingStaff();
        return ResponseEntity.ok(pysuRecordDtos);
    }

    @PutMapping("/change-assignment")
    public ResponseEntity<Void> changeGroupAssignment(
            @RequestParam Long userId,
            @RequestParam Long subjectId,
            @RequestBody AssignedGroupsDto groupsDto) {

        pysuService.changeGroupAssignment(userId, subjectId, groupsDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-assignment")
    public ResponseEntity<Void> deleteGroupAssignment(
            @RequestParam Long userId,
            @RequestParam Long subjectId) {

        pysuService.deleteGroupAssignment(userId, subjectId);
        return ResponseEntity.ok().build();
    }
}
