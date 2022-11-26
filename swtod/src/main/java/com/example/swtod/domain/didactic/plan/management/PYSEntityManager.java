package com.example.swtod.domain.didactic.plan.management;

import com.example.swtod.domain.common.classes.type.ClassesType;
import com.example.swtod.domain.common.classes.type.ClassesTypeRepository;
import com.example.swtod.domain.didactic.plan.PlanYearSubject;
import com.example.swtod.domain.didactic.plan.dto.PYSRecordDto;
import com.example.swtod.domain.didactic.plan.management.transfer.PYSRelatedEntitiesTransporter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.swtod.domain.common.classes.type.ClassesTypeConst.*;

@Configuration
@RequiredArgsConstructor
public class PYSEntityManager {

    private final ClassesTypeRepository classesTypeRepository;

    public void addLectureEntityIfPossible(List<PlanYearSubject> planYearSubjects,
                                           PYSRecordDto PYSRecordDto,
                                           PYSRelatedEntitiesTransporter transporter) {
        if (PYSRecordDto.getLectureHoursNumber() != 0) {
            ClassesType classesType = classesTypeRepository.findClassesTypeByName(LECTURE_NAME).orElseThrow();

            planYearSubjects.add(
                    new PlanYearSubject(
                            PYSRecordDto.getClassesTypeNamesPysIds().get(LECTURE_NAME),
                            PYSRecordDto.getGroupsPerLecture(),
                            PYSRecordDto.getWeeksPerSemester(),
                            PYSRecordDto.getLectureHoursNumberPerWeek(),
                            PYSRecordDto.getNumberOfStudents(),
                            PYSRecordDto.getSemesterType().charAt(0),
                            PYSRecordDto.getYear(),
                            classesType,
                            transporter.getStudiesType(),
                            transporter.getPlanYear(),
                            transporter.getSubject(),
                            transporter.getFaculty()
                    )
            );
        }
    }

    public void addExerciseEntityIfPossible(List<PlanYearSubject> planYearSubjects,
                                            PYSRecordDto PYSRecordDto,
                                            PYSRelatedEntitiesTransporter transporter) {
        if (PYSRecordDto.getExerciseHoursNumber() != 0) {
            ClassesType classesType = classesTypeRepository.findClassesTypeByName(EXERCISE_NAME).orElseThrow();

            planYearSubjects.add(
                    new PlanYearSubject(
                            PYSRecordDto.getClassesTypeNamesPysIds().get(EXERCISE_NAME),
                            PYSRecordDto.getGroupsPerExercise(),
                            PYSRecordDto.getWeeksPerSemester(),
                            PYSRecordDto.getExerciseHoursNumberPerWeek(),
                            PYSRecordDto.getNumberOfStudents(),
                            PYSRecordDto.getSemesterType().charAt(0),
                            PYSRecordDto.getYear(),
                            classesType,
                            transporter.getStudiesType(),
                            transporter.getPlanYear(),
                            transporter.getSubject(),
                            transporter.getFaculty()
                    )
            );
        }
    }

    public void addLaboratoryEntityIfPossible(List<PlanYearSubject> planYearSubjects,
                                              PYSRecordDto PYSRecordDto,
                                              PYSRelatedEntitiesTransporter transporter) {
        if (PYSRecordDto.getLaboratoryHoursNumber() != 0) {
            ClassesType classesType = classesTypeRepository.findClassesTypeByName(LABORATORY_NAME).orElseThrow();

            planYearSubjects.add(
                    new PlanYearSubject(
                            PYSRecordDto.getClassesTypeNamesPysIds().get(LABORATORY_NAME),
                            PYSRecordDto.getGroupsPerLaboratory(),
                            PYSRecordDto.getWeeksPerSemester(),
                            PYSRecordDto.getLaboratoryHoursNumberPerWeek(),
                            PYSRecordDto.getNumberOfStudents(),
                            PYSRecordDto.getSemesterType().charAt(0),
                            PYSRecordDto.getYear(),
                            classesType,
                            transporter.getStudiesType(),
                            transporter.getPlanYear(),
                            transporter.getSubject(),
                            transporter.getFaculty()
                    )
            );
        }
    }

    public void addProjectEntityIfPossible(List<PlanYearSubject> planYearSubjects,
                                           PYSRecordDto PYSRecordDto,
                                           PYSRelatedEntitiesTransporter transporter) {
        if (PYSRecordDto.getProjectHoursNumber() != 0) {
            ClassesType classesType = classesTypeRepository.findClassesTypeByName(PROJECT_NAME).orElseThrow();

            planYearSubjects.add(
                    new PlanYearSubject(
                            PYSRecordDto.getClassesTypeNamesPysIds().get(PROJECT_NAME),
                            PYSRecordDto.getGroupsPerProject(),
                            PYSRecordDto.getWeeksPerSemester(),
                            PYSRecordDto.getProjectHoursNumberPerWeek(),
                            PYSRecordDto.getNumberOfStudents(),
                            PYSRecordDto.getSemesterType().charAt(0),
                            PYSRecordDto.getYear(),
                            classesType,
                            transporter.getStudiesType(),
                            transporter.getPlanYear(),
                            transporter.getSubject(),
                            transporter.getFaculty()
                    )
            );
        }
    }

    public void addSeminaryEntityIfPossible(List<PlanYearSubject> planYearSubjects,
                                            PYSRecordDto PYSRecordDto,
                                            PYSRelatedEntitiesTransporter transporter) {
        if (PYSRecordDto.getSeminaryHoursNumber() != 0) {
            ClassesType classesType = classesTypeRepository.findClassesTypeByName(SEMINARY_NAME).orElseThrow();

            planYearSubjects.add(
                    new PlanYearSubject(
                            PYSRecordDto.getClassesTypeNamesPysIds().get(SEMINARY_NAME),
                            PYSRecordDto.getGroupsPerSeminary(),
                            PYSRecordDto.getWeeksPerSemester(),
                            PYSRecordDto.getSeminaryHoursNumberPerWeek(),
                            PYSRecordDto.getNumberOfStudents(),
                            PYSRecordDto.getSemesterType().charAt(0),
                            PYSRecordDto.getYear(),
                            classesType,
                            transporter.getStudiesType(),
                            transporter.getPlanYear(),
                            transporter.getSubject(),
                            transporter.getFaculty()
                    )
            );
        }
    }

    public void addLectureDtoIfPossible(List<PYSRecordDto> PYSRecordDtos,
                                        PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals(LECTURE_NAME)) {
            PYSRecordDtos.add(
                    new PYSRecordDto(
                            new HashMap<>(Map.ofEntries(Map.entry(LECTURE_NAME, planYearSubject.getId()))),
                            planYearSubject.getId(),
                            planYearSubject.getSubject().getId(),
                            planYearSubject.getFaculty().getName(),
                            planYearSubject.getPlanYear().getYear(),
                            planYearSubject.getPlanYear().getName(),
                            planYearSubject.getSubject().getFieldOfStudies().getName(),
                            planYearSubject.getStudiesType().getName(),
                            planYearSubject.getSubject().getName(),
                            planYearSubject.getWeeksNumber(),
                            planYearSubject.getHoursPerWeek(),
                            0.0,
                            0.0,
                            0.0,
                            0.0,
                            planYearSubject.getStudentsNumber(),
                            planYearSubject.getGroupsNumber(),
                            planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber(),
                            0,
                            0.0,
                            0,
                            0.0,
                            0,
                            0.0,
                            0,
                            0.0,
                            String.valueOf(planYearSubject.getSemester()),
                            planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber()
                    )
            );
        }
    }

    public void addExerciseDtoIfPossible(List<PYSRecordDto> PYSRecordDtos,
                                         PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals(EXERCISE_NAME)) {
            PYSRecordDtos.add(
                    new PYSRecordDto(
                            new HashMap<>(Map.ofEntries(Map.entry(EXERCISE_NAME, planYearSubject.getId()))),
                            planYearSubject.getId(),
                            planYearSubject.getSubject().getId(),
                            planYearSubject.getFaculty().getName(),
                            planYearSubject.getPlanYear().getYear(),
                            planYearSubject.getPlanYear().getName(),
                            planYearSubject.getSubject().getFieldOfStudies().getName(),
                            planYearSubject.getStudiesType().getName(),
                            planYearSubject.getSubject().getName(),
                            planYearSubject.getWeeksNumber(),
                            0.0,
                            planYearSubject.getHoursPerWeek(),
                            0.0,
                            0.0,
                            0.0,
                            planYearSubject.getStudentsNumber(),
                            0,
                            0.0,
                            planYearSubject.getGroupsNumber(),
                            planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber(),
                            0,
                            0.0,
                            0,
                            0.0,
                            0,
                            0.0,
                            String.valueOf(planYearSubject.getSemester()),
                            planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber()
                    )
            );
        }
    }

    public void addLaboratoryDtoIfPossible(List<PYSRecordDto> PYSRecordDtos,
                                           PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals(LABORATORY_NAME)) {
            PYSRecordDtos.add(
                    new PYSRecordDto(
                            new HashMap<>(Map.ofEntries(Map.entry(LABORATORY_NAME, planYearSubject.getId()))),
                            planYearSubject.getId(),
                            planYearSubject.getSubject().getId(),
                            planYearSubject.getFaculty().getName(),
                            planYearSubject.getPlanYear().getYear(),
                            planYearSubject.getPlanYear().getName(),
                            planYearSubject.getSubject().getFieldOfStudies().getName(),
                            planYearSubject.getStudiesType().getName(),
                            planYearSubject.getSubject().getName(),
                            planYearSubject.getWeeksNumber(),
                            0.0,
                            0.0,
                            planYearSubject.getHoursPerWeek(),
                            0.0,
                            0.0,
                            planYearSubject.getStudentsNumber(),
                            0,
                            0.0,
                            0,
                            0.0,
                            planYearSubject.getGroupsNumber(),
                            planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber(),
                            0,
                            0.0,
                            0,
                            0.0,
                            String.valueOf(planYearSubject.getSemester()),
                            planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber()
                    )
            );
        }
    }

    public void addProjectDtoIfPossible(List<PYSRecordDto> PYSRecordDtos,
                                        PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals(PROJECT_NAME)) {
            PYSRecordDtos.add(
                    new PYSRecordDto(
                            new HashMap<>(Map.ofEntries(Map.entry(PROJECT_NAME, planYearSubject.getId()))),
                            planYearSubject.getId(),
                            planYearSubject.getSubject().getId(),
                            planYearSubject.getFaculty().getName(),
                            planYearSubject.getPlanYear().getYear(),
                            planYearSubject.getPlanYear().getName(),
                            planYearSubject.getSubject().getFieldOfStudies().getName(),
                            planYearSubject.getStudiesType().getName(),
                            planYearSubject.getSubject().getName(),
                            planYearSubject.getWeeksNumber(),
                            0.0,
                            0.0,
                            0.0,
                            planYearSubject.getHoursPerWeek(),
                            0.0,
                            planYearSubject.getStudentsNumber(),
                            0,
                            0.0,
                            0,
                            0.0,
                            0,
                            0.0,
                            planYearSubject.getGroupsNumber(),
                            planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber(),
                            0,
                            0.0,
                            String.valueOf(planYearSubject.getSemester()),
                            planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber()
                    )
            );
        }
    }

    public void addSeminaryDtoIfPossible(List<PYSRecordDto> PYSRecordDtos,
                                         PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals(SEMINARY_NAME)) {
            PYSRecordDtos.add(
                    new PYSRecordDto(
                            new HashMap<>(Map.ofEntries(Map.entry(SEMINARY_NAME, planYearSubject.getId()))),
                            planYearSubject.getId(),
                            planYearSubject.getSubject().getId(),
                            planYearSubject.getFaculty().getName(),
                            planYearSubject.getPlanYear().getYear(),
                            planYearSubject.getPlanYear().getName(),
                            planYearSubject.getSubject().getFieldOfStudies().getName(),
                            planYearSubject.getStudiesType().getName(),
                            planYearSubject.getSubject().getName(),
                            planYearSubject.getWeeksNumber(),
                            0.0,
                            0.0,
                            0.0,
                            0.0,
                            planYearSubject.getHoursPerWeek(),
                            planYearSubject.getStudentsNumber(),
                            0,
                            0.0,
                            0,
                            0.0,
                            0,
                            0.0,
                            0,
                            0.0,
                            planYearSubject.getGroupsNumber(),
                            planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber(),
                            String.valueOf(planYearSubject.getSemester()),
                            planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber()
                    )
            );
        }
    }

    public void setDtosLectureFields(PYSRecordDto PYSRecordDto,
                                     PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals(LECTURE_NAME)) {
            PYSRecordDto.addClassTypeNamePysId(LECTURE_NAME, planYearSubject.getId());
            PYSRecordDto.setLectureHoursNumberPerWeek(planYearSubject.getHoursPerWeek());
            PYSRecordDto.setGroupsPerLecture(planYearSubject.getGroupsNumber());
            PYSRecordDto.setLectureHoursNumber(planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber());
        }
    }

    public void setDtosExerciseFields(PYSRecordDto PYSRecordDto,
                                      PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals(EXERCISE_NAME)) {
            PYSRecordDto.addClassTypeNamePysId(EXERCISE_NAME, planYearSubject.getId());
            PYSRecordDto.setExerciseHoursNumberPerWeek(planYearSubject.getHoursPerWeek());
            PYSRecordDto.setGroupsPerExercise(planYearSubject.getGroupsNumber());
            PYSRecordDto.setExerciseHoursNumber(planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber());
        }
    }

    public void setDtosLaboratoryFields(PYSRecordDto PYSRecordDto,
                                        PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals(LABORATORY_NAME)) {
            PYSRecordDto.addClassTypeNamePysId(LABORATORY_NAME, planYearSubject.getId());
            PYSRecordDto.setLaboratoryHoursNumberPerWeek(planYearSubject.getHoursPerWeek());
            PYSRecordDto.setGroupsPerLaboratory(planYearSubject.getGroupsNumber());
            PYSRecordDto.setLaboratoryHoursNumber(planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber());
        }
    }

    public void setDtosProjectFields(PYSRecordDto PYSRecordDto,
                                     PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals(PROJECT_NAME)) {
            PYSRecordDto.addClassTypeNamePysId(PROJECT_NAME, planYearSubject.getId());
            PYSRecordDto.setProjectHoursNumberPerWeek(planYearSubject.getHoursPerWeek());
            PYSRecordDto.setGroupsPerProject(planYearSubject.getGroupsNumber());
            PYSRecordDto.setProjectHoursNumber(planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber());
        }
    }

    public void setDtosSeminaryFields(PYSRecordDto PYSRecordDto,
                                      PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals(SEMINARY_NAME)) {
            PYSRecordDto.addClassTypeNamePysId(SEMINARY_NAME, planYearSubject.getId());
            PYSRecordDto.setLectureHoursNumberPerWeek(planYearSubject.getHoursPerWeek());
            PYSRecordDto.setGroupsPerLecture(planYearSubject.getGroupsNumber());
            PYSRecordDto.setLectureHoursNumber(planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber());
        }
    }
}
