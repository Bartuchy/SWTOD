package com.example.swtod.configs.csv;

import com.example.swtod.domain.didactic.plan.PlanYearSubject;
import com.example.swtod.domain.didactic.plan.dto.PlanYearSubjectDto;

import java.util.List;

public interface CsvMapper {
    List<PlanYearSubjectDto> mapRecordsToDtos(List<List<String>> records, String facultyName);
    List<PlanYearSubject> mapDtosToEntities(List<PlanYearSubjectDto> planYearSubjectDtos);
}
