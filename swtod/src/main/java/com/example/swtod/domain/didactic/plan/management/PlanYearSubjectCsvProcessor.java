package com.example.swtod.domain.didactic.plan.management;

import com.example.swtod.configs.csv.Mapper;
import com.example.swtod.configs.csv.CsvReader;
import com.example.swtod.domain.didactic.plan.PlanYearSubject;
import com.example.swtod.domain.didactic.plan.dto.PlanYearSubjectRecordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class PlanYearSubjectCsvProcessor {
    private final CsvReader csvReader;
    private final Mapper mapper;

    public List<PlanYearSubject> processPlanYearSubjectCsv(MultipartFile file, String facultyName) throws IOException {
        List<List<String>> records = csvReader.writeDataFromFile(file);
        List<PlanYearSubjectRecordDto> planYearSubjectRecordDtos = mapper.mapRecordsToDtos(records, facultyName);
        return mapper.mapDtosToEntities(planYearSubjectRecordDtos);
    }
}
