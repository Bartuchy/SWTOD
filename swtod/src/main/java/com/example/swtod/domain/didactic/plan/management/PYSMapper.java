package com.example.swtod.domain.didactic.plan.management;

import com.example.swtod.common.csv.Mapper;
import com.example.swtod.domain.didactic.plan.PlanYearSubject;
import com.example.swtod.domain.didactic.plan.dto.PYSRecordDto;
import com.example.swtod.domain.didactic.plan.management.transfer.PYSRelatedEntitiesSupplier;
import com.example.swtod.domain.didactic.plan.management.transfer.PYSRelatedEntitiesTransporter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class PYSMapper implements Mapper<PlanYearSubject, PYSRecordDto> {
    private final PYSRelatedEntitiesSupplier supplier;
    private final PYSEntityManager entitiesManager;

    @Override
    public List<PYSRecordDto> mapRecordsToDtos(
            List<List<String>> records,
            String facultyName) {

        List<PYSRecordDto> PYSRecordDtos = new ArrayList<>();

        for (List<String> record : records) {
            List<String> recordsWithZeros = mapEmptyValuesToZeros(record);
            PYSRecordDtos.add(new PYSRecordDto(recordsWithZeros, facultyName));
        }

        return PYSRecordDtos;
    }

    @Override
    public List<PlanYearSubject> mapDtosToEntities(List<PYSRecordDto> PYSRecordDtos) {
        List<PlanYearSubject> planYearSubjects = new ArrayList<>();

        for (PYSRecordDto PYSRecordDto : PYSRecordDtos) {
            splitDtoIntoEntities(planYearSubjects, PYSRecordDto);
        }

        return planYearSubjects;
    }

    @Override
    public List<PYSRecordDto> mapEntitiesToDtos(List<PlanYearSubject> planYearSubjects) {
        List<PYSRecordDto> PYSRecordDtos = new ArrayList<>();
        boolean isDtoPresentInList;

        for (PlanYearSubject planYearSubject : planYearSubjects) {
            isDtoPresentInList = checkDtoPresence(planYearSubject, PYSRecordDtos);

            if (!isDtoPresentInList)
                addDtoIfPossible(PYSRecordDtos, planYearSubject);
        }

        return PYSRecordDtos;
    }

    public boolean checkDtoPresence(PlanYearSubject planYearSubject, List<PYSRecordDto> PYSRecordDtos) {
        for (PYSRecordDto PYSRecordDto : PYSRecordDtos) {
            if (PYSRecordDto.getSubjectName().equals(planYearSubject.getSubject().getName())) {
                setDtoClassesTypeFields(PYSRecordDto, planYearSubject);
                return true;
            }
        }
        return false;
    }

    private void splitDtoIntoEntities(List<PlanYearSubject> planYearSubjects, PYSRecordDto PYSRecordDto) {
        PYSRelatedEntitiesTransporter transporter = supplier.getAllRelatedEntities(PYSRecordDto);
        addEntitiesIfPossible(planYearSubjects, PYSRecordDto, transporter);
    }

    private void addEntitiesIfPossible(List<PlanYearSubject> planYearSubjects, PYSRecordDto PYSRecordDto, PYSRelatedEntitiesTransporter transporter) {
        if (PYSRecordDto.getClassesTypeNamesPysIds() == null) {
            PYSRecordDto.setClassesTypeNamesPysIds(new HashMap<>());
        }
        entitiesManager.addLectureEntityIfPossible(planYearSubjects, PYSRecordDto, transporter);
        entitiesManager.addExerciseEntityIfPossible(planYearSubjects, PYSRecordDto, transporter);
        entitiesManager.addLaboratoryEntityIfPossible(planYearSubjects, PYSRecordDto, transporter);
        entitiesManager.addProjectEntityIfPossible(planYearSubjects, PYSRecordDto, transporter);
        entitiesManager.addSeminaryEntityIfPossible(planYearSubjects, PYSRecordDto, transporter);
    }

    private void setDtoClassesTypeFields(PYSRecordDto PYSRecordDto, PlanYearSubject planYearSubject) {
        entitiesManager.setDtosLectureFields(PYSRecordDto, planYearSubject);
        entitiesManager.setDtosExerciseFields(PYSRecordDto, planYearSubject);
        entitiesManager.setDtosLaboratoryFields(PYSRecordDto, planYearSubject);
        entitiesManager.setDtosProjectFields(PYSRecordDto, planYearSubject);
        entitiesManager.setDtosSeminaryFields(PYSRecordDto, planYearSubject);
    }

    private void addDtoIfPossible(List<PYSRecordDto> PYSRecordDtos, PlanYearSubject planYearSubject) {
        entitiesManager.addLectureDtoIfPossible(PYSRecordDtos, planYearSubject);
        entitiesManager.addExerciseDtoIfPossible(PYSRecordDtos, planYearSubject);
        entitiesManager.addLaboratoryDtoIfPossible(PYSRecordDtos, planYearSubject);
        entitiesManager.addProjectDtoIfPossible(PYSRecordDtos, planYearSubject);
        entitiesManager.addSeminaryDtoIfPossible(PYSRecordDtos, planYearSubject);
    }
}
