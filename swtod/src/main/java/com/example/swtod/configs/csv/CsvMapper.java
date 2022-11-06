package com.example.swtod.configs.csv;

import com.example.swtod.domain.didactic.plan.PlanYearSubject;
import com.example.swtod.domain.didactic.plan.dto.PlanYearSubjectRecordDto;

import java.util.List;

public interface CsvMapper {
    List<PlanYearSubjectRecordDto> mapRecordsToDtos(List<List<String>> records, String facultyName);
    List<PlanYearSubject> mapDtosToEntities(List<PlanYearSubjectRecordDto> planYearSubjectRecordDtos);
}
