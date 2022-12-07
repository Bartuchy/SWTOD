package com.example.swtod.domain.teaching.staff;

import com.example.swtod.domain.teaching.staff.dto.AssignedGroupsDto;
import com.example.swtod.domain.teaching.staff.dto.PYSURecordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/plan-year-subject-user")
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

    @GetMapping("/academic-years")
    public ResponseEntity<List<String>> getAcademicYears() {
        List<String> academicYears = pysuService.getAcademicYearsDistinct();
        return ResponseEntity.ok(academicYears);
    }

    @GetMapping("/filters")
    public ResponseEntity<List<PYSURecordDto>> filterTeachingStaff(
            @RequestParam String academicYear,
            @RequestParam(required = false) String userNameSurname,
            @RequestParam(required = false) String subjectName) {

        List<PYSURecordDto> pysuRecordDtos = pysuService
                .getTeachingStaff(academicYear, userNameSurname, subjectName);

        return ResponseEntity.ok(pysuRecordDtos);
    }

    @GetMapping(value = "/{userId}/report", produces = "text/csv")
    public ResponseEntity<Resource> getReportForUser(@PathVariable Long userId) {
        String reportName = String.format("user_%d_report.csv", userId);

        ByteArrayInputStream reportByteArray = pysuService.generateReportForUser(userId);
        InputStreamResource reportFile = new InputStreamResource(reportByteArray);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + reportName)
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(reportFile);
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
