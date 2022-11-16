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
}
