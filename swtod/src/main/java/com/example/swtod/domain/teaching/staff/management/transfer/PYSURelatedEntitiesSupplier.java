package com.example.swtod.domain.teaching.staff.management.transfer;

import com.example.swtod.common.exception.UserNotFoundException;
import com.example.swtod.domain.common.status.Status;
import com.example.swtod.domain.common.status.StatusRepository;
import com.example.swtod.domain.didactic.plan.PlanYearSubject;
import com.example.swtod.domain.didactic.plan.PYSRepository;
import com.example.swtod.domain.teaching.staff.dto.PYSURecordDto;
import com.example.swtod.domain.user.User;
import com.example.swtod.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class PYSURelatedEntitiesSupplier {
    private final PYSRepository PYSRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;

    public PYSURelatedEntitiesTransporter getAllRelatedEntities(Long userId, Long subjectId, Long statusId) {
        return new PYSURelatedEntitiesTransporter(
                this.getUser(userId),
                this.getPlanYearSubject(subjectId),
                this.getStatus(statusId)
        );
    }

    public PYSURelatedEntitiesTransporter getAllRelatedEntities(PYSURecordDto recordDto) {
        return new PYSURelatedEntitiesTransporter(
                this.getUser(recordDto.getUserId()),
                this.getPlanYearSubject(recordDto.getSubjectId()),
                this.getStatus(recordDto.getStatusName())
        );
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException(String.format("User with id '%d' not found", userId)));

    }

    private List<PlanYearSubject> getPlanYearSubject(Long subjectId) {
        return PYSRepository.findPlanYearSubjectBySubjectId(subjectId);
    }

    private Status getStatus(Long statusId) {
        return statusRepository
                .findById(statusId)
                .orElseThrow();
    }

    private Status getStatus(String statusName) {
        return statusRepository
                .findStatusByName(statusName);
    }
}
