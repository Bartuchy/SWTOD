package com.example.swtod.domain.didactic.plan;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/plan-year-subject")
public class PlanYearSubjectController {
    private final PlanYearSubjectService planYearSubjectService;

    @PostMapping(value = "/upload-file", consumes = "multipart/form-data")
    public ResponseEntity<Void> uploadCsv(@RequestParam("PZD") MultipartFile csvFile, @RequestParam String facultyName) {
        try {
            planYearSubjectService.savePlanYearSubjects(csvFile, facultyName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<Void> removeDidacticPlan() {
        planYearSubjectService.removeDidacticPlan();
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/all")
//    public ResponseEntity<List<PlanYearSubjectDto>> get() {
//        return ResponseEntity.ok(planYearSubjectCsvReader.writeDataFromFile());
//    }
}
