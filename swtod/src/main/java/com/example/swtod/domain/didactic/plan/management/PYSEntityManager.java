package com.example.swtod.domain.didactic.plan.management;

import com.example.swtod.domain.common.classes.type.ClassesType;
import com.example.swtod.domain.common.classes.type.ClassesTypeRepository;
import com.example.swtod.domain.didactic.plan.PlanYearSubject;
import com.example.swtod.domain.didactic.plan.dto.PlanYearSubjectRecordDto;
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
                                           PlanYearSubjectRecordDto planYearSubjectRecordDto,
                                           PYSRelatedEntitiesTransporter transporter) {
        if (planYearSubjectRecordDto.getLectureHoursNumber() != 0) {
            ClassesType classesType = classesTypeRepository.findClassesTypeByName(LECTURE_NAME).orElseThrow();

            planYearSubjects.add(
                    new PlanYearSubject(
                            planYearSubjectRecordDto.getClassesTypeNamesPysIds().get(LECTURE_NAME),
                            planYearSubjectRecordDto.getGroupsPerLecture(),
                            planYearSubjectRecordDto.getWeeksPerSemester(),
                            planYearSubjectRecordDto.getLectureHoursNumberPerWeek(),
                            planYearSubjectRecordDto.getNumberOfStudents(),
                            planYearSubjectRecordDto.getSemesterType().charAt(0),
                            planYearSubjectRecordDto.getYear(),
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
                                            PlanYearSubjectRecordDto planYearSubjectRecordDto,
                                            PYSRelatedEntitiesTransporter transporter) {
        if (planYearSubjectRecordDto.getExerciseHoursNumber() != 0) {
            ClassesType classesType = classesTypeRepository.findClassesTypeByName(EXERCISE_NAME).orElseThrow();

            planYearSubjects.add(
                    new PlanYearSubject(
                            planYearSubjectRecordDto.getClassesTypeNamesPysIds().get(EXERCISE_NAME),
                            planYearSubjectRecordDto.getGroupsPerExercise(),
                            planYearSubjectRecordDto.getWeeksPerSemester(),
                            planYearSubjectRecordDto.getExerciseHoursNumberPerWeek(),
                            planYearSubjectRecordDto.getNumberOfStudents(),
                            planYearSubjectRecordDto.getSemesterType().charAt(0),
                            planYearSubjectRecordDto.getYear(),
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
                                              PlanYearSubjectRecordDto planYearSubjectRecordDto,
                                              PYSRelatedEntitiesTransporter transporter) {
        if (planYearSubjectRecordDto.getLaboratoryHoursNumber() != 0) {
            ClassesType classesType = classesTypeRepository.findClassesTypeByName(LABORATORY_NAME).orElseThrow();

            planYearSubjects.add(
                    new PlanYearSubject(
                            planYearSubjectRecordDto.getClassesTypeNamesPysIds().get(LABORATORY_NAME),
                            planYearSubjectRecordDto.getGroupsPerLaboratory(),
                            planYearSubjectRecordDto.getWeeksPerSemester(),
                            planYearSubjectRecordDto.getLaboratoryHoursNumberPerWeek(),
                            planYearSubjectRecordDto.getNumberOfStudents(),
                            planYearSubjectRecordDto.getSemesterType().charAt(0),
                            planYearSubjectRecordDto.getYear(),
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
                                           PlanYearSubjectRecordDto planYearSubjectRecordDto,
                                           PYSRelatedEntitiesTransporter transporter) {
        if (planYearSubjectRecordDto.getProjectHoursNumber() != 0) {
            ClassesType classesType = classesTypeRepository.findClassesTypeByName(PROJECT_NAME).orElseThrow();

            planYearSubjects.add(
                    new PlanYearSubject(
                            planYearSubjectRecordDto.getClassesTypeNamesPysIds().get(PROJECT_NAME),
                            planYearSubjectRecordDto.getGroupsPerProject(),
                            planYearSubjectRecordDto.getWeeksPerSemester(),
                            planYearSubjectRecordDto.getProjectHoursNumberPerWeek(),
                            planYearSubjectRecordDto.getNumberOfStudents(),
                            planYearSubjectRecordDto.getSemesterType().charAt(0),
                            planYearSubjectRecordDto.getYear(),
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
                                            PlanYearSubjectRecordDto planYearSubjectRecordDto,
                                            PYSRelatedEntitiesTransporter transporter) {
        if (planYearSubjectRecordDto.getSeminaryHoursNumber() != 0) {
            ClassesType classesType = classesTypeRepository.findClassesTypeByName(SEMINARY_NAME).orElseThrow();

            planYearSubjects.add(
                    new PlanYearSubject(
                            planYearSubjectRecordDto.getClassesTypeNamesPysIds().get(SEMINARY_NAME),
                            planYearSubjectRecordDto.getGroupsPerSeminary(),
                            planYearSubjectRecordDto.getWeeksPerSemester(),
                            planYearSubjectRecordDto.getSeminaryHoursNumberPerWeek(),
                            planYearSubjectRecordDto.getNumberOfStudents(),
                            planYearSubjectRecordDto.getSemesterType().charAt(0),
                            planYearSubjectRecordDto.getYear(),
                            classesType,
                            transporter.getStudiesType(),
                            transporter.getPlanYear(),
                            transporter.getSubject(),
                            transporter.getFaculty()
                    )
            );
        }
    }

    public void addLectureDtoIfPossible(List<PlanYearSubjectRecordDto> planYearSubjectRecordDtos,
                                        PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals(LECTURE_NAME)) {
            planYearSubjectRecordDtos.add(
                    new PlanYearSubjectRecordDto(
                            new HashMap<>(Map.ofEntries(Map.entry(LECTURE_NAME, planYearSubject.getId()))),
                            planYearSubject.getId(),
                            planYearSubject.getSubject().getId(),
                            planYearSubject.getFaculty().getName(),
                            planYearSubject.getPlanYear().getYear(),
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

    public void addExerciseDtoIfPossible(List<PlanYearSubjectRecordDto> planYearSubjectRecordDtos,
                                         PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals(EXERCISE_NAME)) {
            planYearSubjectRecordDtos.add(
                    new PlanYearSubjectRecordDto(
                            new HashMap<>(Map.ofEntries(Map.entry(EXERCISE_NAME, planYearSubject.getId()))),
                            planYearSubject.getId(),
                            planYearSubject.getSubject().getId(),
                            planYearSubject.getFaculty().getName(),
                            planYearSubject.getPlanYear().getYear(),
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

    public void addLaboratoryDtoIfPossible(List<PlanYearSubjectRecordDto> planYearSubjectRecordDtos,
                                           PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals(LABORATORY_NAME)) {
            planYearSubjectRecordDtos.add(
                    new PlanYearSubjectRecordDto(
                            new HashMap<>(Map.ofEntries(Map.entry(LABORATORY_NAME, planYearSubject.getId()))),
                            planYearSubject.getId(),
                            planYearSubject.getSubject().getId(),
                            planYearSubject.getFaculty().getName(),
                            planYearSubject.getPlanYear().getYear(),
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

    public void addProjectDtoIfPossible(List<PlanYearSubjectRecordDto> planYearSubjectRecordDtos,
                                        PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals(PROJECT_NAME)) {
            planYearSubjectRecordDtos.add(
                    new PlanYearSubjectRecordDto(
                            new HashMap<>(Map.ofEntries(Map.entry(PROJECT_NAME, planYearSubject.getId()))),
                            planYearSubject.getId(),
                            planYearSubject.getSubject().getId(),
                            planYearSubject.getFaculty().getName(),
                            planYearSubject.getPlanYear().getYear(),
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

    public void addSeminaryDtoIfPossible(List<PlanYearSubjectRecordDto> planYearSubjectRecordDtos,
                                         PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals(SEMINARY_NAME)) {
            planYearSubjectRecordDtos.add(
                    new PlanYearSubjectRecordDto(
                            new HashMap<>(Map.ofEntries(Map.entry(SEMINARY_NAME, planYearSubject.getId()))),
                            planYearSubject.getId(),
                            planYearSubject.getSubject().getId(),
                            planYearSubject.getFaculty().getName(),
                            planYearSubject.getPlanYear().getYear(),
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

    public void setDtosLectureFields(PlanYearSubjectRecordDto planYearSubjectRecordDto,
                                     PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals(LECTURE_NAME)) {
            planYearSubjectRecordDto.addClassTypeNamePysId(LECTURE_NAME, planYearSubject.getId());
            planYearSubjectRecordDto.setLectureHoursNumberPerWeek(planYearSubject.getHoursPerWeek());
            planYearSubjectRecordDto.setGroupsPerLecture(planYearSubject.getGroupsNumber());
            planYearSubjectRecordDto.setLectureHoursNumber(planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber());
        }
    }

    public void setDtosExerciseFields(PlanYearSubjectRecordDto planYearSubjectRecordDto,
                                      PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals(EXERCISE_NAME)) {
            planYearSubjectRecordDto.addClassTypeNamePysId(EXERCISE_NAME, planYearSubject.getId());
            planYearSubjectRecordDto.setExerciseHoursNumberPerWeek(planYearSubject.getHoursPerWeek());
            planYearSubjectRecordDto.setGroupsPerExercise(planYearSubject.getGroupsNumber());
            planYearSubjectRecordDto.setExerciseHoursNumber(planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber());
        }
    }

    public void setDtosLaboratoryFields(PlanYearSubjectRecordDto planYearSubjectRecordDto,
                                        PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals(LABORATORY_NAME)) {
            planYearSubjectRecordDto.addClassTypeNamePysId(LABORATORY_NAME, planYearSubject.getId());
            planYearSubjectRecordDto.setLaboratoryHoursNumberPerWeek(planYearSubject.getHoursPerWeek());
            planYearSubjectRecordDto.setGroupsPerLaboratory(planYearSubject.getGroupsNumber());
            planYearSubjectRecordDto.setLaboratoryHoursNumber(planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber());
        }
    }

    public void setDtosProjectFields(PlanYearSubjectRecordDto planYearSubjectRecordDto,
                                     PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals(PROJECT_NAME)) {
            planYearSubjectRecordDto.addClassTypeNamePysId(PROJECT_NAME, planYearSubject.getId());
            planYearSubjectRecordDto.setProjectHoursNumberPerWeek(planYearSubject.getHoursPerWeek());
            planYearSubjectRecordDto.setGroupsPerProject(planYearSubject.getGroupsNumber());
            planYearSubjectRecordDto.setProjectHoursNumber(planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber());
        }
    }

    public void setDtosSeminaryFields(PlanYearSubjectRecordDto planYearSubjectRecordDto,
                                      PlanYearSubject planYearSubject) {
        if (planYearSubject.getClassesType().getName().equals(SEMINARY_NAME)) {
            planYearSubjectRecordDto.addClassTypeNamePysId(SEMINARY_NAME, planYearSubject.getId());
            planYearSubjectRecordDto.setLectureHoursNumberPerWeek(planYearSubject.getHoursPerWeek());
            planYearSubjectRecordDto.setGroupsPerLecture(planYearSubject.getGroupsNumber());
            planYearSubjectRecordDto.setLectureHoursNumber(planYearSubject.getHoursPerWeek() * planYearSubject.getWeeksNumber());
        }
    }
}
