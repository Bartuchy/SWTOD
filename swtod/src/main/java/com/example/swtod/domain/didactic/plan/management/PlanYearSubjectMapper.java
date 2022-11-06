package com.example.swtod.domain.didactic.plan.management;

import com.example.swtod.configs.csv.CsvMapper;
import com.example.swtod.domain.classes.type.ClassesTypeRepository;
import com.example.swtod.domain.didactic.plan.PlanYearSubject;
import com.example.swtod.domain.didactic.plan.dto.PlanYearSubjectRecordDto;
import com.example.swtod.domain.didactic.plan.management.transfer.PYSRelatedEntitiesSupplier;
import com.example.swtod.domain.didactic.plan.management.transfer.PYSRelatedEntitiesTransporter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Configuration
@RequiredArgsConstructor
public class PlanYearSubjectMapper implements CsvMapper {
    private final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    private final ClassesTypeRepository classesTypeRepository;
    private final PYSRelatedEntitiesSupplier supplier;
    private final PYSEntitiesManager entitiesManager;

    public List<PlanYearSubjectRecordDto> mapRecordsToDtos(List<List<String>> records, String facultyName) {

        List<PlanYearSubjectRecordDto> planYearSubjectRecordDtos = new ArrayList<>();

        for (List<String> record : records) {
            List<String> recordsWithZeros = mapEmptyValuesToZeros(record);
            planYearSubjectRecordDtos.add(new PlanYearSubjectRecordDto(recordsWithZeros, facultyName));
        }

        return planYearSubjectRecordDtos;
    }

    public List<PlanYearSubject> mapDtosToEntities(List<PlanYearSubjectRecordDto> planYearSubjectRecordDtos) {
        List<PlanYearSubject> planYearSubjects = new ArrayList<>();

        for (PlanYearSubjectRecordDto planYearSubjectRecordDto : planYearSubjectRecordDtos) {
            splitDtoIntoEntities(planYearSubjects, planYearSubjectRecordDto);
        }

        return planYearSubjects;
    }

    public List<PlanYearSubjectRecordDto> mapEntitiesToDtos(List<PlanYearSubject> planYearSubjects) {
        List<PlanYearSubjectRecordDto> planYearSubjectRecordDtos = new ArrayList<>();
        boolean isDtoPresentInListPresent;

        for (PlanYearSubject planYearSubject : planYearSubjects) {
            isDtoPresentInListPresent = checkDtoPresence(planYearSubject, planYearSubjectRecordDtos);

            if (!isDtoPresentInListPresent)
                addDtosIfPossible(planYearSubjectRecordDtos, planYearSubject);
        }

        return planYearSubjectRecordDtos;
    }

    private void splitDtoIntoEntities(List<PlanYearSubject> planYearSubjects, PlanYearSubjectRecordDto planYearSubjectRecordDto) {
        PYSRelatedEntitiesTransporter transporter = supplier.getAllRelatedEntities(planYearSubjectRecordDto);
        addEntitiesIfPossible(planYearSubjects, planYearSubjectRecordDto, transporter);
    }

    private void addEntitiesIfPossible(List<PlanYearSubject> planYearSubjects, PlanYearSubjectRecordDto planYearSubjectRecordDto, PYSRelatedEntitiesTransporter transporter) {
        entitiesManager.addLectureEntityIfPossible(planYearSubjects, planYearSubjectRecordDto, transporter);
        entitiesManager.addExerciseEntityIfPossible(planYearSubjects, planYearSubjectRecordDto, transporter);
        entitiesManager.addLaboratoryEntityIfPossible(planYearSubjects, planYearSubjectRecordDto, transporter);
        entitiesManager.addProjectEntityIfPossible(planYearSubjects, planYearSubjectRecordDto, transporter);
        entitiesManager.addSeminaryEntityIfPossible(planYearSubjects, planYearSubjectRecordDto, transporter);
    }

    private void setDtoClassesTypeFields(PlanYearSubjectRecordDto planYearSubjectRecordDto, PlanYearSubject planYearSubject) {
        entitiesManager.setDtosLectureFields(planYearSubjectRecordDto, planYearSubject);
        entitiesManager.setDtosExerciseFields(planYearSubjectRecordDto, planYearSubject);
        entitiesManager.setDtosLaboratoryFields(planYearSubjectRecordDto, planYearSubject);
        entitiesManager.setDtosProjectFields(planYearSubjectRecordDto, planYearSubject);
        entitiesManager.setDtosSeminaryFields(planYearSubjectRecordDto, planYearSubject);
    }

    private boolean checkDtoPresence(PlanYearSubject planYearSubject, List<PlanYearSubjectRecordDto> planYearSubjectRecordDtos) {
        for (PlanYearSubjectRecordDto planYearSubjectRecordDto : planYearSubjectRecordDtos) {
            if (planYearSubjectRecordDto.getSubjectName().equals(planYearSubject.getSubject().getName())) {
                setDtoClassesTypeFields(planYearSubjectRecordDto, planYearSubject);
                return true;
            }
        }
        return false;
    }

    private void addDtosIfPossible(List<PlanYearSubjectRecordDto> planYearSubjectRecordDtos, PlanYearSubject planYearSubject) {
        entitiesManager.addLectureDtoIfPossible(planYearSubjectRecordDtos, planYearSubject);
        entitiesManager.addExerciseDtoIfPossible(planYearSubjectRecordDtos, planYearSubject);
        entitiesManager.addLaboratoryDtoIfPossible(planYearSubjectRecordDtos, planYearSubject);
        entitiesManager.addProjectDtoIfPossible(planYearSubjectRecordDtos, planYearSubject);
        entitiesManager.addSeminaryDtoIfPossible(planYearSubjectRecordDtos, planYearSubject);
    }

    private List<String> mapEmptyValuesToZeros(List<String> values) {
        List<String> mappedValues = new ArrayList<>();
        for (String value : values) {
            addElemToMappingList(value, mappedValues);
        }
        return mappedValues;
    }

    private void addElemToMappingList(String str, List<String> mappedValues) {
        if (Objects.equals(str, "")) {
            mappedValues.add("0");
        } else {
            mappedValues.add(str);
        }
    }

    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }
}
