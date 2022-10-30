package com.example.swtod.domain.didactic.plan.management;

import com.example.swtod.configs.csv.CsvMapper;
import com.example.swtod.configs.csv.CsvReader;
import com.example.swtod.domain.didactic.plan.PlanYearSubject;
import com.example.swtod.domain.didactic.plan.dto.PlanYearSubjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class PlanYearSubjectCsvProcessor {
    private final CsvReader csvReader;
    private final CsvMapper csvMapper;

    public List<PlanYearSubject> processPlanYearSubjectCsv(MultipartFile file, String facultyName) throws IOException {
        List<List<String>> records = csvReader.writeDataFromFile(file);
        List<PlanYearSubjectDto> planYearSubjectDtos = csvMapper.mapRecordsToDtos(records, facultyName);
        return csvMapper.mapDtosToEntities(planYearSubjectDtos);
    }
}
