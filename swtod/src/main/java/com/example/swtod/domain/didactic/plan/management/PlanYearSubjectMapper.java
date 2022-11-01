package com.example.swtod.domain.didactic.plan.management;

import com.example.swtod.configs.csv.CsvMapper;
import com.example.swtod.domain.classes.type.ClassesTypeRepository;
import com.example.swtod.domain.didactic.plan.PlanYearSubject;
import com.example.swtod.domain.didactic.plan.dto.PlanYearSubjectDto;
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

    public List<PlanYearSubjectDto> mapRecordsToDtos(List<List<String>> records, String facultyName) {

        List<PlanYearSubjectDto> planYearSubjectDtos = new ArrayList<>();

        for (List<String> record : records) {
            List<String> recordsWithZeros = mapEmptyValuesToZeros(record);
            planYearSubjectDtos.add(new PlanYearSubjectDto(recordsWithZeros, facultyName));
        }

        return planYearSubjectDtos;
    }

    public List<PlanYearSubject> mapDtosToEntities(List<PlanYearSubjectDto> planYearSubjectDtos) {
        List<PlanYearSubject> planYearSubjects = new ArrayList<>();

        for (PlanYearSubjectDto planYearSubjectDto : planYearSubjectDtos) {
            splitDtoIntoEntities(planYearSubjects, planYearSubjectDto);
        }

        return planYearSubjects;
    }

    public List<PlanYearSubjectDto> mapEntitiesToDtos(List<PlanYearSubject> planYearSubjects) {
        List<PlanYearSubjectDto> planYearSubjectDtos = new ArrayList<>();
        boolean isDtoPresentInListPresent;

        for (PlanYearSubject planYearSubject : planYearSubjects) {
            isDtoPresentInListPresent = checkDtoPresence(planYearSubject, planYearSubjectDtos);

            if (!isDtoPresentInListPresent)
                addDtosIfPossible(planYearSubjectDtos, planYearSubject);
        }

        return planYearSubjectDtos;
    }

    private void splitDtoIntoEntities(List<PlanYearSubject> planYearSubjects, PlanYearSubjectDto planYearSubjectDto) {
        PYSRelatedEntitiesTransporter transporter = supplier.getAllRelatedEntities(planYearSubjectDto);
        addEntitiesIfPossible(planYearSubjects, planYearSubjectDto, transporter);
    }

    private void addEntitiesIfPossible(List<PlanYearSubject> planYearSubjects, PlanYearSubjectDto planYearSubjectDto, PYSRelatedEntitiesTransporter transporter) {
        entitiesManager.addLectureEntityIfPossible(planYearSubjects, planYearSubjectDto, transporter);
        entitiesManager.addExerciseEntityIfPossible(planYearSubjects, planYearSubjectDto, transporter);
        entitiesManager.addLaboratoryEntityIfPossible(planYearSubjects, planYearSubjectDto, transporter);
        entitiesManager.addProjectEntityIfPossible(planYearSubjects, planYearSubjectDto, transporter);
        entitiesManager.addSeminaryEntityIfPossible(planYearSubjects, planYearSubjectDto, transporter);
    }

    private void setDtoClassesTypeFields(PlanYearSubjectDto planYearSubjectDto, PlanYearSubject planYearSubject) {
        entitiesManager.setDtosLectureFields(planYearSubjectDto, planYearSubject);
        entitiesManager.setDtosExerciseFields(planYearSubjectDto, planYearSubject);
        entitiesManager.setDtosLaboratoryFields(planYearSubjectDto, planYearSubject);
        entitiesManager.setDtosProjectFields(planYearSubjectDto, planYearSubject);
        entitiesManager.setDtosSeminaryFields(planYearSubjectDto, planYearSubject);
    }

    private boolean checkDtoPresence(PlanYearSubject planYearSubject, List<PlanYearSubjectDto> planYearSubjectDtos) {
        for (PlanYearSubjectDto planYearSubjectDto : planYearSubjectDtos) {
            if (planYearSubjectDto.getSubjectName().equals(planYearSubject.getSubject().getName())) {
                setDtoClassesTypeFields(planYearSubjectDto, planYearSubject);
                return true;
            }
        }
        return false;
    }

    private void addDtosIfPossible(List<PlanYearSubjectDto> planYearSubjectDtos, PlanYearSubject planYearSubject) {
        entitiesManager.addLectureDtoIfPossible(planYearSubjectDtos, planYearSubject);
        entitiesManager.addExerciseDtoIfPossible(planYearSubjectDtos, planYearSubject);
        entitiesManager.addLaboratoryDtoIfPossible(planYearSubjectDtos, planYearSubject);
        entitiesManager.addProjectDtoIfPossible(planYearSubjectDtos, planYearSubject);
        entitiesManager.addSeminaryDtoIfPossible(planYearSubjectDtos, planYearSubject);
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
