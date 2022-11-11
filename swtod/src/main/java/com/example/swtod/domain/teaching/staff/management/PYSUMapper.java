package com.example.swtod.domain.teaching.staff.management;

import com.example.swtod.configs.csv.Mapper;
import com.example.swtod.domain.teaching.staff.PlanYearSubjectUser;
import com.example.swtod.domain.teaching.staff.dto.AssignedGroupsDto;
import com.example.swtod.domain.teaching.staff.dto.PYSURecordDto;
import com.example.swtod.domain.teaching.staff.management.transfer.PYSURelatedEntitiesSupplier;
import com.example.swtod.domain.teaching.staff.management.transfer.PYSURelatedEntitiesTransporter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class PYSUMapper implements Mapper<PlanYearSubjectUser, PYSURecordDto> {
    private final PYSURelatedEntitiesSupplier supplier;
    private final PYSUEntityManager manager;

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

    public List<PlanYearSubjectUser> mapRequestDataToEntity(Long userId, Long subjectId, AssignedGroupsDto groupsDto) {
        PYSURelatedEntitiesTransporter transporter = supplier.getAllRelatedEntities(userId, subjectId, 1L);
        List<PlanYearSubjectUser> planYearSubjectUsers = new ArrayList<>();

        addEntitiesIfPossible(planYearSubjectUsers, groupsDto, transporter);
        return planYearSubjectUsers;
    }

    private void addEntitiesIfPossible(List<PlanYearSubjectUser> planYearSubjectUsers,
                                       AssignedGroupsDto groupsDto,
                                       PYSURelatedEntitiesTransporter transporter) {
        manager.assignGroupToLectureIfPossible(planYearSubjectUsers, groupsDto, transporter);
        manager.assignGroupToExerciseIfPossible(planYearSubjectUsers, groupsDto, transporter);
        manager.assignGroupToLaboratoryIfPossible(planYearSubjectUsers, groupsDto, transporter);
        manager.assignGroupToProjectIfPossible(planYearSubjectUsers, groupsDto, transporter);
        manager.assignGroupToSeminaryIfPossible(planYearSubjectUsers, groupsDto, transporter);
    }
}
