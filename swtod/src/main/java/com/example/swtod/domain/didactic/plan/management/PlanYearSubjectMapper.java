package com.example.swtod.domain.didactic.plan.management;

import com.example.swtod.configs.csv.CsvMapper;
import com.example.swtod.domain.classes.type.ClassesType;
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

    private void splitDtoIntoEntities(List<PlanYearSubject> planYearSubjects, PlanYearSubjectDto planYearSubjectDto) {
        PYSRelatedEntitiesTransporter transporter = supplier.getAllRelatedEntities(planYearSubjectDto);
        addEntitiesIfPossible(planYearSubjects, planYearSubjectDto, transporter);
    }

    private void addEntitiesIfPossible(List<PlanYearSubject> planYearSubjects, PlanYearSubjectDto planYearSubjectDto, PYSRelatedEntitiesTransporter transporter) {
        addLectureEntityIfPossible(planYearSubjects, planYearSubjectDto, transporter);
        addExerciseEntityIfPossible(planYearSubjects, planYearSubjectDto, transporter);
        addLaboratoryEntityIfPossible(planYearSubjects, planYearSubjectDto, transporter);
        addProjectEntityIfPossible(planYearSubjects, planYearSubjectDto, transporter);
        addSeminaryEntityIfPossible(planYearSubjects, planYearSubjectDto, transporter);
    }

    private void addLectureEntityIfPossible(List<PlanYearSubject> planYearSubjects,
                                            PlanYearSubjectDto planYearSubjectDto,
                                            PYSRelatedEntitiesTransporter transporter) {
        if (planYearSubjectDto.getLectureHoursNumber() != 0) {
            ClassesType classesType = classesTypeRepository.findClassesTypeByName("W").orElseThrow();

            planYearSubjects.add(
                    new PlanYearSubject(
                            planYearSubjectDto.getGroupsPerLecture(),
                            planYearSubjectDto.getWeeksPerSemester(),
                            planYearSubjectDto.getLectureHoursNumberPerWeek(),
                            planYearSubjectDto.getNumberOfStudents(),
                            planYearSubjectDto.getSemesterType().charAt(0),
                            planYearSubjectDto.getYear(),
                            classesType,
                            transporter.getStudiesType(),
                            transporter.getPlanYear(),
                            transporter.getSubject(),
                            transporter.getFaculty()
                    )
            );
        }
    }

    private void addExerciseEntityIfPossible(List<PlanYearSubject> planYearSubjects,
                                             PlanYearSubjectDto planYearSubjectDto,
                                             PYSRelatedEntitiesTransporter transporter) {
        if (planYearSubjectDto.getExerciseHoursNumber() != 0) {
            ClassesType classesType = classesTypeRepository.findClassesTypeByName("E").orElseThrow();

            planYearSubjects.add(
                    new PlanYearSubject(
                            planYearSubjectDto.getGroupsPerExercise(),
                            planYearSubjectDto.getWeeksPerSemester(),
                            planYearSubjectDto.getExerciseHoursNumberPerWeek(),
                            planYearSubjectDto.getNumberOfStudents(),
                            planYearSubjectDto.getSemesterType().charAt(0),
                            planYearSubjectDto.getYear(),
                            classesType,
                            transporter.getStudiesType(),
                            transporter.getPlanYear(),
                            transporter.getSubject(),
                            transporter.getFaculty()
                    )
            );
        }
    }

    private void addLaboratoryEntityIfPossible(List<PlanYearSubject> planYearSubjects,
                                               PlanYearSubjectDto planYearSubjectDto,
                                               PYSRelatedEntitiesTransporter transporter) {
        if (planYearSubjectDto.getLaboratoryHoursNumber() != 0) {
            ClassesType classesType = classesTypeRepository.findClassesTypeByName("L").orElseThrow();

            planYearSubjects.add(
                    new PlanYearSubject(
                            planYearSubjectDto.getGroupsPerLaboratory(),
                            planYearSubjectDto.getWeeksPerSemester(),
                            planYearSubjectDto.getLaboratoryHoursNumberPerWeek(),
                            planYearSubjectDto.getNumberOfStudents(),
                            planYearSubjectDto.getSemesterType().charAt(0),
                            planYearSubjectDto.getYear(),
                            classesType,
                            transporter.getStudiesType(),
                            transporter.getPlanYear(),
                            transporter.getSubject(),
                            transporter.getFaculty()
                    )
            );
        }
    }

    private void addProjectEntityIfPossible(List<PlanYearSubject> planYearSubjects,
                                            PlanYearSubjectDto planYearSubjectDto,
                                            PYSRelatedEntitiesTransporter transporter) {
        if (planYearSubjectDto.getProjectHoursNumber() != 0) {
            ClassesType classesType = classesTypeRepository.findClassesTypeByName("P").orElseThrow();

            planYearSubjects.add(
                    new PlanYearSubject(
                            planYearSubjectDto.getGroupsPerProject(),
                            planYearSubjectDto.getWeeksPerSemester(),
                            planYearSubjectDto.getProjectHoursNumberPerWeek(),
                            planYearSubjectDto.getNumberOfStudents(),
                            planYearSubjectDto.getSemesterType().charAt(0),
                            planYearSubjectDto.getYear(),
                            classesType,
                            transporter.getStudiesType(),
                            transporter.getPlanYear(),
                            transporter.getSubject(),
                            transporter.getFaculty()
                    )
            );
        }
    }

    private void addSeminaryEntityIfPossible(List<PlanYearSubject> planYearSubjects,
                                             PlanYearSubjectDto planYearSubjectDto,
                                             PYSRelatedEntitiesTransporter transporter) {
        if (planYearSubjectDto.getSeminaryHoursNumber() != 0) {
            ClassesType classesType = classesTypeRepository.findClassesTypeByName("S").orElseThrow();

            planYearSubjects.add(
                    new PlanYearSubject(
                            planYearSubjectDto.getGroupsPerSeminary(),
                            planYearSubjectDto.getWeeksPerSemester(),
                            planYearSubjectDto.getSeminaryHoursNumberPerWeek(),
                            planYearSubjectDto.getNumberOfStudents(),
                            planYearSubjectDto.getSemesterType().charAt(0),
                            planYearSubjectDto.getYear(),
                            classesType,
                            transporter.getStudiesType(),
                            transporter.getPlanYear(),
                            transporter.getSubject(),
                            transporter.getFaculty()
                    )
            );
        }
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
