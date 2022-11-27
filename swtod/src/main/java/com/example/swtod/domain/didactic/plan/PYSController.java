package com.example.swtod.domain.didactic.plan;

import com.example.swtod.domain.didactic.plan.dto.PYSRecordDto;
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
public class PYSController {
    private final PYSService pysService;

    @PostMapping(value = "/upload-file", consumes = "multipart/form-data")
    public ResponseEntity<Void> uploadPlanYearSubjectCsv(
            @RequestParam("PZD") MultipartFile csvFile,
            @RequestParam String facultyName,
            @RequestParam String academicYear) {

        try {
            pysService.savePlanYearSubjects(csvFile, facultyName, academicYear);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<Void> removeDidacticPlan() {
        pysService.removeDidacticPlan();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PYSRecordDto> getDidacticPlanById(@PathVariable Long id) {
        PYSRecordDto dto = pysService.getSubjectBySubjectId(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PYSRecordDto>> getDidacticPlan() {
        List<PYSRecordDto> pysRecordDtos = pysService.getDidacticPlan();
        return ResponseEntity.ok(pysRecordDtos);
    }


    @GetMapping("/academic-year")
    public ResponseEntity<List<PYSRecordDto>> getDidacticPlanByAcademicYear(@RequestParam String academicYear) {
        List<PYSRecordDto> pysRecordDtos = pysService.getDidacticPlanByAcademicYear(academicYear);
        return ResponseEntity.ok(pysRecordDtos);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addPlanYearSubjectRecord(@RequestBody PYSRecordDto recordDto) {
        pysService.savePlanYearSubjectRecord(recordDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{subjectId}/update")
    public ResponseEntity<Void> editPlanYearSubjectRecord(@PathVariable Long subjectId,
                                                          @RequestBody PYSRecordDto recordDto) {
        pysService.updatePlanYearSubjectRecord(subjectId, recordDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{subjectId}/delete")
    public ResponseEntity<Void> deletePlanYearSubjectRecord(@PathVariable Long subjectId) {
        pysService.removeDidacticPlanRecord(subjectId);
        return ResponseEntity.ok().build();
    }
}
