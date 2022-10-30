package com.example.swtod.domain.didactic.plan;

import com.example.swtod.domain.didactic.plan.dto.PlanYearSubjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/plan-year-subject")
public class PlanYearSubjectController {
    private final PlanYearSubjectService planYearSubjectService;

    @PostMapping(value = "/upload-file", consumes = "multipart/form-data")
    public ResponseEntity<Void> uploadCsv(@RequestParam("PZD") MultipartFile csvFile, @RequestParam String facultyName) {
        List<PlanYearSubjectDto> planYearSubjectDtos;
        try {
            //planYearSubjectDtos = planYearSubjectCsvProcessor.processPlanYearSubjectCsv(csvFile);
            planYearSubjectService.savePlanYearSubjects(csvFile, facultyName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/all")
//    public ResponseEntity<List<PlanYearSubjectDto>> get() {
//        return ResponseEntity.ok(planYearSubjectCsvReader.writeDataFromFile());
//    }
}
