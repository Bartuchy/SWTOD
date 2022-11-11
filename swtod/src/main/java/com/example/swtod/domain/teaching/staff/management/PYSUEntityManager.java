package com.example.swtod.domain.teaching.staff.management;

import com.example.swtod.domain.didactic.plan.PlanYearSubject;
import com.example.swtod.domain.teaching.staff.PlanYearSubjectUser;
import com.example.swtod.domain.teaching.staff.dto.AssignedGroupsDto;
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

    private PlanYearSubject getPlanYearSubjectIfPossible(PYSURelatedEntitiesTransporter transporter, String classTypeName) {
        Optional<PlanYearSubject> planYearSubject = transporter
                .getPlanYearSubjects()
                .stream()
                .filter(pys -> pys.getClassesType().getName().equals(classTypeName))
                .findFirst();

        return planYearSubject.orElse(null);
    }
}
