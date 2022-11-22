package com.example.swtod.domain.teaching.staff.management;

import com.example.swtod.domain.didactic.plan.PlanYearSubject;
import com.example.swtod.domain.teaching.staff.PlanYearSubjectUser;
import com.example.swtod.domain.teaching.staff.dto.AssignedGroupsDto;
import com.example.swtod.domain.teaching.staff.dto.PYSURecordDto;
import com.example.swtod.domain.teaching.staff.management.transfer.PYSURelatedEntitiesTransporter;
import org.springframework.context.annotation.Configuration;

import java.util.*;

import static com.example.swtod.domain.common.classes.type.ClassesTypeConst.*;

@Configuration
public class PYSUEntityManager {

    public void assignGroupToLectureIfPossible(List<PlanYearSubjectUser> planYearSubjectUsers,
                                               AssignedGroupsDto groupsDto,
                                               PYSURelatedEntitiesTransporter transporter) {
        if (groupsDto.getLectureGroupsNumber() > 0) {
            PlanYearSubject planYearSubject = getPlanYearSubjectIfPossible(transporter, LECTURE_NAME);

            planYearSubjectUsers.add(
                    new PlanYearSubjectUser(
                            groupsDto.getClassesTypeNamesPysuIds().get(LECTURE_NAME),
                            groupsDto.getLectureGroupsNumber(),
                            "--",
                            planYearSubject,
                            transporter.getUser(),
                            transporter.getStatus()
                    )
            );
        }
    }

    public void assignGroupToExerciseIfPossible(List<PlanYearSubjectUser> planYearSubjectUsers,
                                                AssignedGroupsDto groupsDto,
                                                PYSURelatedEntitiesTransporter transporter) {
        if (groupsDto.getExerciseGroupsNumber() > 0) {
            PlanYearSubject planYearSubject = getPlanYearSubjectIfPossible(transporter, EXERCISE_NAME);

            planYearSubjectUsers.add(
                    new PlanYearSubjectUser(
                            groupsDto.getClassesTypeNamesPysuIds().get(EXERCISE_NAME),
                            groupsDto.getExerciseGroupsNumber(),
                            "--",
                            planYearSubject,
                            transporter.getUser(),
                            transporter.getStatus()
                    )
            );
        }
    }

    public void assignGroupToLaboratoryIfPossible(List<PlanYearSubjectUser> planYearSubjectUsers,
                                                  AssignedGroupsDto groupsDto,
                                                  PYSURelatedEntitiesTransporter transporter) {
        if (groupsDto.getLaboratoryGroupsNumber() > 0) {
            PlanYearSubject planYearSubject = getPlanYearSubjectIfPossible(transporter, LABORATORY_NAME);

            planYearSubjectUsers.add(
                    new PlanYearSubjectUser(
                            groupsDto.getClassesTypeNamesPysuIds().get(LABORATORY_NAME),
                            groupsDto.getLaboratoryGroupsNumber(),
                            "--",
                            planYearSubject,
                            transporter.getUser(),
                            transporter.getStatus()
                    )
            );
        }
    }

    public void assignGroupToProjectIfPossible(List<PlanYearSubjectUser> planYearSubjectUsers,
                                               AssignedGroupsDto groupsDto,
                                               PYSURelatedEntitiesTransporter transporter) {
        if (groupsDto.getProjectGroupsNumber() > 0) {
            PlanYearSubject planYearSubject = getPlanYearSubjectIfPossible(transporter, PROJECT_NAME);

            planYearSubjectUsers.add(
                    new PlanYearSubjectUser(
                            groupsDto.getClassesTypeNamesPysuIds().get(PROJECT_NAME),
                            groupsDto.getProjectGroupsNumber(),
                            "--",
                            planYearSubject,
                            transporter.getUser(),
                            transporter.getStatus()
                    ));
        }
    }

    public void assignGroupToSeminaryIfPossible(List<PlanYearSubjectUser> planYearSubjectUsers,
                                                AssignedGroupsDto groupsDto,
                                                PYSURelatedEntitiesTransporter transporter) {
        if (groupsDto.getSeminaryGroupsNumber() > 0) {
            PlanYearSubject planYearSubject = getPlanYearSubjectIfPossible(transporter, SEMINARY_NAME);

            planYearSubjectUsers.add(
                    new PlanYearSubjectUser(
                            groupsDto.getClassesTypeNamesPysuIds().get(SEMINARY_NAME),
                            groupsDto.getSeminaryGroupsNumber(),
                            "--",
                            planYearSubject,
                            transporter.getUser(),
                            transporter.getStatus()
                    ));
        }
    }


    public void addLectureDtoIfPossible(List<PYSURecordDto> pysuRecordDtos, PlanYearSubjectUser planYearSubjectUser) {
        if (planYearSubjectUser.getPlanYearSubject().getClassesType().getName().equals(LECTURE_NAME)) {
            pysuRecordDtos.add(
                    new PYSURecordDto(
                            new HashMap<>(Map.ofEntries(Map.entry(LECTURE_NAME, planYearSubjectUser.getId()))),
                            planYearSubjectUser.getUser().getId(),
                            planYearSubjectUser.getPlanYearSubject().getSubject().getId(),
                            planYearSubjectUser.getUser().getName() + " " + planYearSubjectUser.getUser().getSurname(),
                            planYearSubjectUser.getPlanYearSubject().getFaculty().getName(),
                            planYearSubjectUser.getPlanYearSubject().getSubject().getName(),
                            planYearSubjectUser.getPlanYearSubject().getSubject().getFieldOfStudies().getName(),
                            planYearSubjectUser.getPlanYearSubject().getStudiesType().getName(),
                            planYearSubjectUser.getPlanYearSubject().getPlanYear().getYear(),
                            planYearSubjectUser.getPlanYearSubject().getSemesterNumber(),
                            planYearSubjectUser.getPlanYearSubject().getWeeksNumber(),
                            planYearSubjectUser.getPlanYearSubject().getHoursPerWeek(),
                            planYearSubjectUser.getGroupsNumber(),
                            0.0,
                            0,
                            0.0,
                            0,
                            0.0,
                            0,
                            0.0,
                            0,
                            planYearSubjectUser.getStatus().getName()

                    )
            );
        }
    }

    public void addExerciseDtoIfPossible(List<PYSURecordDto> pysuRecordDtos, PlanYearSubjectUser planYearSubjectUser) {
        if (planYearSubjectUser.getPlanYearSubject().getClassesType().getName().equals(EXERCISE_NAME)) {
            pysuRecordDtos.add(
                    new PYSURecordDto(
                            new HashMap<>(Map.ofEntries(Map.entry(EXERCISE_NAME, planYearSubjectUser.getId()))),
                            planYearSubjectUser.getUser().getId(),
                            planYearSubjectUser.getPlanYearSubject().getSubject().getId(),
                            planYearSubjectUser.getUser().getName() + " " + planYearSubjectUser.getUser().getSurname(),
                            planYearSubjectUser.getPlanYearSubject().getFaculty().getName(),
                            planYearSubjectUser.getPlanYearSubject().getSubject().getName(),
                            planYearSubjectUser.getPlanYearSubject().getSubject().getFieldOfStudies().getName(),
                            planYearSubjectUser.getPlanYearSubject().getStudiesType().getName(),
                            planYearSubjectUser.getPlanYearSubject().getPlanYear().getYear(),
                            planYearSubjectUser.getPlanYearSubject().getSemesterNumber(),
                            planYearSubjectUser.getPlanYearSubject().getWeeksNumber(),
                            0.0,
                            0,
                            planYearSubjectUser.getPlanYearSubject().getHoursPerWeek(),
                            planYearSubjectUser.getGroupsNumber(),
                            0.0,
                            0,
                            0.0,
                            0,
                            0.0,
                            0,
                            planYearSubjectUser.getStatus().getName()

                    )
            );
        }
    }

    public void addLaboratoryDtoIfPossible(List<PYSURecordDto> pysuRecordDtos, PlanYearSubjectUser planYearSubjectUser) {
        if (planYearSubjectUser.getPlanYearSubject().getClassesType().getName().equals(LABORATORY_NAME)) {
            pysuRecordDtos.add(
                    new PYSURecordDto(
                            new HashMap<>(Map.ofEntries(Map.entry(LABORATORY_NAME, planYearSubjectUser.getId()))),
                            planYearSubjectUser.getUser().getId(),
                            planYearSubjectUser.getPlanYearSubject().getSubject().getId(),
                            planYearSubjectUser.getUser().getName() + " " + planYearSubjectUser.getUser().getSurname(),
                            planYearSubjectUser.getPlanYearSubject().getFaculty().getName(),
                            planYearSubjectUser.getPlanYearSubject().getSubject().getName(),
                            planYearSubjectUser.getPlanYearSubject().getSubject().getFieldOfStudies().getName(),
                            planYearSubjectUser.getPlanYearSubject().getStudiesType().getName(),
                            planYearSubjectUser.getPlanYearSubject().getPlanYear().getYear(),
                            planYearSubjectUser.getPlanYearSubject().getSemesterNumber(),
                            planYearSubjectUser.getPlanYearSubject().getWeeksNumber(),
                            0.0,
                            0,
                            0.0,
                            0,
                            planYearSubjectUser.getPlanYearSubject().getHoursPerWeek(),
                            planYearSubjectUser.getGroupsNumber(),
                            0.0,
                            0,
                            0.0,
                            0,
                            planYearSubjectUser.getStatus().getName()

                    )
            );
        }
    }

    public void addProjectDtoIfPossible(List<PYSURecordDto> pysuRecordDtos, PlanYearSubjectUser planYearSubjectUser) {
        if (planYearSubjectUser.getPlanYearSubject().getClassesType().getName().equals(PROJECT_NAME)) {
            pysuRecordDtos.add(
                    new PYSURecordDto(
                            new HashMap<>(Map.ofEntries(Map.entry(PROJECT_NAME, planYearSubjectUser.getId()))),
                            planYearSubjectUser.getUser().getId(),
                            planYearSubjectUser.getPlanYearSubject().getSubject().getId(),
                            planYearSubjectUser.getUser().getName() + " " + planYearSubjectUser.getUser().getSurname(),
                            planYearSubjectUser.getPlanYearSubject().getFaculty().getName(),
                            planYearSubjectUser.getPlanYearSubject().getSubject().getName(),
                            planYearSubjectUser.getPlanYearSubject().getSubject().getFieldOfStudies().getName(),
                            planYearSubjectUser.getPlanYearSubject().getStudiesType().getName(),
                            planYearSubjectUser.getPlanYearSubject().getPlanYear().getYear(),
                            planYearSubjectUser.getPlanYearSubject().getSemesterNumber(),
                            planYearSubjectUser.getPlanYearSubject().getWeeksNumber(),
                            0.0,
                            0,
                            0.0,
                            0,
                            0.0,
                            0,
                            planYearSubjectUser.getPlanYearSubject().getHoursPerWeek(),
                            planYearSubjectUser.getGroupsNumber(),
                            0.0,
                            0,
                            planYearSubjectUser.getStatus().getName()

                    )
            );
        }
    }

    public void addSeminaryDtoIfPossible(List<PYSURecordDto> pysuRecordDtos, PlanYearSubjectUser planYearSubjectUser) {
        if (planYearSubjectUser.getPlanYearSubject().getClassesType().getName().equals(SEMINARY_NAME)) {
            pysuRecordDtos.add(
                    new PYSURecordDto(
                            new HashMap<>(Map.ofEntries(Map.entry(SEMINARY_NAME, planYearSubjectUser.getId()))),
                            planYearSubjectUser.getUser().getId(),
                            planYearSubjectUser.getPlanYearSubject().getSubject().getId(),
                            planYearSubjectUser.getUser().getName() + " " + planYearSubjectUser.getUser().getSurname(),
                            planYearSubjectUser.getPlanYearSubject().getFaculty().getName(),
                            planYearSubjectUser.getPlanYearSubject().getSubject().getName(),
                            planYearSubjectUser.getPlanYearSubject().getSubject().getFieldOfStudies().getName(),
                            planYearSubjectUser.getPlanYearSubject().getStudiesType().getName(),
                            planYearSubjectUser.getPlanYearSubject().getPlanYear().getYear(),
                            planYearSubjectUser.getPlanYearSubject().getSemesterNumber(),
                            planYearSubjectUser.getPlanYearSubject().getWeeksNumber(),
                            0.0,
                            0,
                            0.0,
                            0,
                            0.0,
                            0,
                            0.0,
                            0,
                            planYearSubjectUser.getPlanYearSubject().getHoursPerWeek(),
                            planYearSubjectUser.getGroupsNumber(),
                            planYearSubjectUser.getStatus().getName()

                    )
            );
        }
    }

    public void setDtoLectureFields(PYSURecordDto pysuRecordDto, PlanYearSubjectUser planYearSubjectUser) {
        if (planYearSubjectUser.getPlanYearSubject().getClassesType().getName().equals(LECTURE_NAME)) {
            pysuRecordDto.addClassTypeNamePysuId(LECTURE_NAME, planYearSubjectUser.getId());
            pysuRecordDto.setLectureHoursPerWeek(planYearSubjectUser.getPlanYearSubject().getHoursPerWeek());
            pysuRecordDto.setGroupsPerLecture(planYearSubjectUser.getGroupsNumber());
        }
    }

    public void setDtoExerciseFields(PYSURecordDto pysuRecordDto, PlanYearSubjectUser planYearSubjectUser) {
        if (planYearSubjectUser.getPlanYearSubject().getClassesType().getName().equals(EXERCISE_NAME)) {
            pysuRecordDto.addClassTypeNamePysuId(EXERCISE_NAME, planYearSubjectUser.getId());
            pysuRecordDto.setExerciseHoursPerWeek(planYearSubjectUser.getPlanYearSubject().getHoursPerWeek());
            pysuRecordDto.setGroupsPerExercise(planYearSubjectUser.getGroupsNumber());
        }
    }

    public void setDtoLaboratoryFields(PYSURecordDto pysuRecordDto, PlanYearSubjectUser planYearSubjectUser) {
        if (planYearSubjectUser.getPlanYearSubject().getClassesType().getName().equals(LABORATORY_NAME)) {
            pysuRecordDto.addClassTypeNamePysuId(LABORATORY_NAME, planYearSubjectUser.getId());
            pysuRecordDto.setLaboratoryHoursPerWeek(planYearSubjectUser.getPlanYearSubject().getHoursPerWeek());
            pysuRecordDto.setGroupsPerLaboratory(planYearSubjectUser.getGroupsNumber());
        }
    }

    public void setDtoProjectFields(PYSURecordDto pysuRecordDto, PlanYearSubjectUser planYearSubjectUser) {
        if (planYearSubjectUser.getPlanYearSubject().getClassesType().getName().equals(PROJECT_NAME)) {
            pysuRecordDto.addClassTypeNamePysuId(PROJECT_NAME, planYearSubjectUser.getId());
            pysuRecordDto.setProjectHoursPerWeek(planYearSubjectUser.getPlanYearSubject().getHoursPerWeek());
            pysuRecordDto.setGroupsPerProject(planYearSubjectUser.getGroupsNumber());
        }
    }

    public void setDtoSeminaryFields(PYSURecordDto pysuRecordDto, PlanYearSubjectUser planYearSubjectUser) {
        if (planYearSubjectUser.getPlanYearSubject().getClassesType().getName().equals(SEMINARY_NAME)) {
            pysuRecordDto.addClassTypeNamePysuId(SEMINARY_NAME, planYearSubjectUser.getId());
            pysuRecordDto.setSeminaryHoursPerWeek(planYearSubjectUser.getPlanYearSubject().getHoursPerWeek());
            pysuRecordDto.setGroupsPerSeminary(planYearSubjectUser.getGroupsNumber());
        }
    }

    private PlanYearSubject getPlanYearSubjectIfPossible(PYSURelatedEntitiesTransporter transporter, String classTypeName) {
        Optional<PlanYearSubject> planYearSubject = transporter
                .getPlanYearSubjects()
                .stream()
                .filter(pys -> pys.getClassesType().getName().equals(classTypeName))
                .findFirst();

        return planYearSubject.orElse(null);
    }
}
