package com.example.swtod.domain.pensum;

import com.example.swtod.domain.pensum.dto.PensumRecordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pensum")
public class PensumController {
    private final PensumService pensumService;

    @GetMapping
    public ResponseEntity<List<PensumRecordDto>> getPensumForAll() {
        List<PensumRecordDto> recordDtos = pensumService.getPensumForAll();
        return ResponseEntity.ok(recordDtos);
    }
}
