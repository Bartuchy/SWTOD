package com.example.swtod.domain.didactic.plan.management;

import com.example.swtod.common.csv.CsvReader;
import com.example.swtod.domain.didactic.plan.PlanYearSubject;
import com.example.swtod.domain.didactic.plan.dto.PYSRecordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class PYSCsvProcessor {
    private final CsvReader csvReader;
    private final PYSMapper mapper;

    public List<PlanYearSubject> processPlanYearSubjectCsv(MultipartFile file, String facultyName) throws IOException {
        List<List<String>> records = csvReader.writeDataFromFile(file);
        List<PYSRecordDto> PYSRecordDtos = mapper.mapRecordsToDtos(records, facultyName);
        return mapper.mapDtosToEntities(PYSRecordDtos);
    }
}
