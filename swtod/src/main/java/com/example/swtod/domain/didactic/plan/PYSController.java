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
    private final PYSService PYSService;

    @PostMapping(value = "/upload-file", consumes = "multipart/form-data")
    public ResponseEntity<Void> uploadPlanYearSubjectCsv(
            @RequestParam("PZD") MultipartFile csvFile,
            @RequestParam String facultyName) {

        try {
            PYSService.savePlanYearSubjects(csvFile, facultyName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<Void> removeDidacticPlan() {
        PYSService.removeDidacticPlan();
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<PYSRecordDto> getById(@PathVariable Long id) {
        PYSRecordDto dto = PYSService.getSubjectBySubjectId(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PYSRecordDto>> get() {
        List<PYSRecordDto> PYSRecordDtos = PYSService.getDidacticPlan();
        return ResponseEntity.ok(PYSRecordDtos);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addPlanYearSubjectSingle(@RequestBody PYSRecordDto recordDto) {
        PYSService.savePlanYearSubjectSingle(recordDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{subjectId}/update")
    public ResponseEntity<Void> editPlanYearSubjectSingle(@PathVariable Long subjectId, @RequestBody PYSRecordDto recordDto) {
        PYSService.updatePlanYearSubjectSingle(subjectId, recordDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{subjectId}/delete")
    public ResponseEntity<Void> deletePlanYearSubjectSingle(@PathVariable Long subjectId) {
        PYSService.removeDidacticPlanSingle(subjectId);
        return ResponseEntity.ok().build();
    }
}
