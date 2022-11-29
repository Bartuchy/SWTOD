package com.example.swtod.domain.teaching.staff.management;

import com.example.swtod.common.csv.Mapper;
import com.example.swtod.domain.teaching.staff.PlanYearSubjectUser;
import com.example.swtod.domain.teaching.staff.dto.AssignedGroupsDto;
import com.example.swtod.domain.teaching.staff.dto.PYSURecordDto;
import com.example.swtod.domain.teaching.staff.management.transfer.PYSURelatedEntitiesSupplier;
import com.example.swtod.domain.teaching.staff.management.transfer.PYSURelatedEntitiesTransporter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.swtod.domain.common.status.StatusConst.PENDING_STATUS_ID;

@Configuration
@RequiredArgsConstructor
public class PYSUMapper implements Mapper<PlanYearSubjectUser, PYSURecordDto> {
    private final PYSURelatedEntitiesSupplier supplier;
    private final PYSUEntityManager manager;

    @Override
    public List<PYSURecordDto> mapRecordsToDtos(List<List<String>> records, String facultyName, String academicYear) {
        List<PYSURecordDto> pysuRecordDtos = new ArrayList<>();

        for (List<String> record : records) {
            List<String> recordsWithZeros = mapEmptyValuesToZeros(record);
            //pysuRecordDtos.add(new PYSURecordDto(recordsWithZeros, facultyName));
        }

        return pysuRecordDtos;
    }

    @Override
    public List<PlanYearSubjectUser> mapDtosToEntities(List<PYSURecordDto> planYearSubjectRecordDtos) {
        List<PlanYearSubjectUser> planYearSubjectUsers = new ArrayList<>();

        for (PYSURecordDto recordDto: planYearSubjectRecordDtos) {
            splitDtoIntoEntities(planYearSubjectUsers, recordDto);
        }

        return planYearSubjectUsers;
    }

    @Override
    public List<PYSURecordDto> mapEntitiesToDtos(List<PlanYearSubjectUser> planYearSubjectUsers) {
        List<PYSURecordDto> pysuRecordDtos = new ArrayList<>();
        boolean isDtoPresentInList;

        for (PlanYearSubjectUser planYearSubjectUser : planYearSubjectUsers) {
            isDtoPresentInList = checkDtoPresence(planYearSubjectUser, pysuRecordDtos);
            if (!isDtoPresentInList) {
                addDtoIfPossible(pysuRecordDtos, planYearSubjectUser);
            }
        }

        return pysuRecordDtos;
    }

    public List<PlanYearSubjectUser> mapRequestDataToEntity(Long userId, Long subjectId, AssignedGroupsDto groupsDto) {
        PYSURelatedEntitiesTransporter transporter = supplier.getAllRelatedEntities(userId, subjectId, PENDING_STATUS_ID);
        List<PlanYearSubjectUser> planYearSubjectUsers = new ArrayList<>();

        addEntitiesIfPossible(planYearSubjectUsers, groupsDto, transporter);
        return planYearSubjectUsers;
    }

    private void splitDtoIntoEntities(List<PlanYearSubjectUser> planYearSubjectUsers, PYSURecordDto recordDto) {
        PYSURelatedEntitiesTransporter transporter = supplier.getAllRelatedEntities(recordDto);
        AssignedGroupsDto groupsDto = new AssignedGroupsDto(recordDto);

        addEntitiesIfPossible(planYearSubjectUsers, groupsDto, transporter);
    }

    private boolean checkDtoPresence(PlanYearSubjectUser planYearSubjectUser, List<PYSURecordDto> pysuRecordDtos) {
        for (PYSURecordDto pysuRecordDto : pysuRecordDtos) {
            if (pysuRecordDto.getSubjectId().equals(planYearSubjectUser.getPlanYearSubject().getSubject().getId()) &&
                    pysuRecordDto.getUserId().equals(planYearSubjectUser.getUser().getId())) {
                setDtoClassesTypeFieldsIfPossible(pysuRecordDto, planYearSubjectUser);
                return true;
            }
        }
        return false;
    }

    private void addEntitiesIfPossible(List<PlanYearSubjectUser> planYearSubjectUsers,
                                       AssignedGroupsDto groupsDto,
                                       PYSURelatedEntitiesTransporter transporter) {
        if (groupsDto.getClassesTypeNamesPysuIds() == null) {
            groupsDto.setClassesTypeNamesPysuIds(new HashMap<>());
        }
        manager.assignGroupToLectureIfPossible(planYearSubjectUsers, groupsDto, transporter);
        manager.assignGroupToExerciseIfPossible(planYearSubjectUsers, groupsDto, transporter);
        manager.assignGroupToLaboratoryIfPossible(planYearSubjectUsers, groupsDto, transporter);
        manager.assignGroupToProjectIfPossible(planYearSubjectUsers, groupsDto, transporter);
        manager.assignGroupToSeminaryIfPossible(planYearSubjectUsers, groupsDto, transporter);
    }

    private void setDtoClassesTypeFieldsIfPossible(PYSURecordDto pysuRecordDto, PlanYearSubjectUser planYearSubjectUser) {
        manager.setDtoLectureFields(pysuRecordDto, planYearSubjectUser);
        manager.setDtoExerciseFields(pysuRecordDto, planYearSubjectUser);
        manager.setDtoLaboratoryFields(pysuRecordDto, planYearSubjectUser);
        manager.setDtoProjectFields(pysuRecordDto, planYearSubjectUser);
        manager.setDtoSeminaryFields(pysuRecordDto, planYearSubjectUser);
    }

    private void addDtoIfPossible(List<PYSURecordDto> pysuRecordDtos, PlanYearSubjectUser planYearSubjectUser) {
        manager.addLectureDtoIfPossible(pysuRecordDtos, planYearSubjectUser);
        manager.addExerciseDtoIfPossible(pysuRecordDtos, planYearSubjectUser);
        manager.addLaboratoryDtoIfPossible(pysuRecordDtos, planYearSubjectUser);
        manager.addProjectDtoIfPossible(pysuRecordDtos, planYearSubjectUser);
        manager.addSeminaryDtoIfPossible(pysuRecordDtos, planYearSubjectUser);
    }
}
