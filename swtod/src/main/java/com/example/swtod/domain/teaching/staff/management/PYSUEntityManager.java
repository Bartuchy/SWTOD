package com.example.swtod.domain.teaching.staff.management;

import com.example.swtod.domain.didactic.plan.PlanYearSubject;
import com.example.swtod.domain.teaching.staff.PlanYearSubjectUser;
import com.example.swtod.domain.teaching.staff.dto.AssignedGroupsDto;
import com.example.swtod.domain.teaching.staff.dto.PYSURecordDto;
import com.example.swtod.domain.teaching.staff.management.transfer.PYSURelatedEntitiesTransporter;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class PYSUEntityManager {

    public void assignGroupToLectureIfPossible(List<PlanYearSubjectUser> planYearSubjectUsers,
                                               AssignedGroupsDto groupsDto,
                                               PYSURelatedEntitiesTransporter transporter) {
        if (groupsDto.getLectureGroupsNumber() > 0) {
            PlanYearSubject planYearSubject = getPlanYearSubjectIfPossible(transporter, "W");

            planYearSubjectUsers.add(
                    new PlanYearSubjectUser(
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
            PlanYearSubject planYearSubject = getPlanYearSubjectIfPossible(transporter, "C");

            planYearSubjectUsers.add(
                    new PlanYearSubjectUser(
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
            PlanYearSubject planYearSubject = getPlanYearSubjectIfPossible(transporter, "L");

            planYearSubjectUsers.add(
                    new PlanYearSubjectUser(
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
            PlanYearSubject planYearSubject = getPlanYearSubjectIfPossible(transporter, "P");

            planYearSubjectUsers.add(
                    new PlanYearSubjectUser(
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
            PlanYearSubject planYearSubject = getPlanYearSubjectIfPossible(transporter, "S");

            planYearSubjectUsers.add(
                    new PlanYearSubjectUser(
                            groupsDto.getSeminaryGroupsNumber(),
                            "--",
                            planYearSubject,
                            transporter.getUser(),
                            transporter.getStatus()
                    ));
        }
    }


    public void addLectureDtoIfPossible(List<PYSURecordDto> pysuRecordDtos, PlanYearSubjectUser planYearSubjectUser) {
        if (planYearSubjectUser.getPlanYearSubject().getClassesType().getName().equals("W")) {
            pysuRecordDtos.add(
                    new PYSURecordDto(
                            planYearSubjectUser.getUser().getId(),
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
        if (planYearSubjectUser.getPlanYearSubject().getClassesType().getName().equals("C")) {
            pysuRecordDtos.add(
                    new PYSURecordDto(
                            planYearSubjectUser.getUser().getId(),
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
        if (planYearSubjectUser.getPlanYearSubject().getClassesType().getName().equals("L")) {
            pysuRecordDtos.add(
                    new PYSURecordDto(
                            planYearSubjectUser.getUser().getId(),
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
        if (planYearSubjectUser.getPlanYearSubject().getClassesType().getName().equals("P")) {
            pysuRecordDtos.add(
                    new PYSURecordDto(
                            planYearSubjectUser.getUser().getId(),
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
        if (planYearSubjectUser.getPlanYearSubject().getClassesType().getName().equals("S")) {
            pysuRecordDtos.add(
                    new PYSURecordDto(
                            planYearSubjectUser.getUser().getId(),
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
        if (planYearSubjectUser.getPlanYearSubject().getClassesType().getName().equals("W")) {
            pysuRecordDto.setLectureHoursPerWeek(planYearSubjectUser.getPlanYearSubject().getHoursPerWeek());
            pysuRecordDto.setGroupsPerLecture(planYearSubjectUser.getGroupsNumber());
        }
    }

    public void setDtoExerciseFields(PYSURecordDto pysuRecordDto, PlanYearSubjectUser planYearSubjectUser) {
        if (planYearSubjectUser.getPlanYearSubject().getClassesType().getName().equals("C")) {
            pysuRecordDto.setExerciseHoursPerWeek(planYearSubjectUser.getPlanYearSubject().getHoursPerWeek());
            pysuRecordDto.setGroupsPerExercise(planYearSubjectUser.getGroupsNumber());
        }
    }

    public void setDtoLaboratoryFields(PYSURecordDto pysuRecordDto, PlanYearSubjectUser planYearSubjectUser) {
        if (planYearSubjectUser.getPlanYearSubject().getClassesType().getName().equals("L")) {
            pysuRecordDto.setLaboratoryHoursPerWeek(planYearSubjectUser.getPlanYearSubject().getHoursPerWeek());
            pysuRecordDto.setGroupsPerLaboratory(planYearSubjectUser.getGroupsNumber());
        }
    }

    public void setDtoProjectFields(PYSURecordDto pysuRecordDto, PlanYearSubjectUser planYearSubjectUser) {
        if (planYearSubjectUser.getPlanYearSubject().getClassesType().getName().equals("P")) {
            pysuRecordDto.setProjectHoursPerWeek(planYearSubjectUser.getPlanYearSubject().getHoursPerWeek());
            pysuRecordDto.setGroupsPerProject(planYearSubjectUser.getGroupsNumber());
        }
    }

    public void setDtoSeminaryFields(PYSURecordDto pysuRecordDto, PlanYearSubjectUser planYearSubjectUser) {
        if (planYearSubjectUser.getPlanYearSubject().getClassesType().getName().equals("S")) {
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
