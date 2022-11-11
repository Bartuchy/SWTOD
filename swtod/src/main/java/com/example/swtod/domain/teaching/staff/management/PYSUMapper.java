package com.example.swtod.domain.teaching.staff.management;

import com.example.swtod.configs.csv.Mapper;
import com.example.swtod.domain.didactic.plan.PlanYearSubject;
import com.example.swtod.domain.didactic.plan.dto.PlanYearSubjectRecordDto;
import com.example.swtod.domain.teaching.staff.PlanYearSubjectUser;
import com.example.swtod.domain.teaching.staff.dto.PYSURecordDto;

import java.util.ArrayList;
import java.util.List;

public class PYSUMapper implements Mapper<PlanYearSubjectUser, PYSURecordDto> {
    @Override
    public List<PYSURecordDto> mapRecordsToDtos(List<List<String>> records, String facultyName) {
        List<PYSURecordDto> pysuRecordDtos = new ArrayList<>();

        for (List<String> record : records) {
            List<String> recordsWithZeros = mapEmptyValuesToZeros(record);
            //pysuRecordDtos.add(new PYSURecordDto(recordsWithZeros, facultyName));
        }

        return pysuRecordDtos;
    }

    @Override
    public List<PlanYearSubjectUser> mapDtosToEntities(List<PYSURecordDto> planYearSubjectRecordDtos) {
        return null;
    }

    @Override
    public List<PYSURecordDto> mapEntitiesToDtos(List<PlanYearSubjectUser> planYearSubjects) {
        return null;
    }
}
