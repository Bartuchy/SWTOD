package com.example.swtod.domain.pensum;

import com.example.swtod.domain.pensum.dto.PensumRecordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{userId}")
    public ResponseEntity<PensumRecordDto> getPensumByUSerId(@PathVariable Long userId) {
        PensumRecordDto pensumRecordDto = pensumService.getPensumByUserId(userId);
        return ResponseEntity.ok(pensumRecordDto);
    }

    @PutMapping("/{userId}/update")
    public ResponseEntity<Void> updateUserPensum(@PathVariable Long userId, @RequestParam Integer pensum) {
        pensumService.updateUserPensum(userId, pensum);
        return ResponseEntity.ok().build();
    }
}
