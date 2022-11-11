package com.example.swtod.domain.teaching.staff.management.transfer;

import com.example.swtod.domain.common.status.Status;
import com.example.swtod.domain.didactic.plan.PlanYearSubject;
import com.example.swtod.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PYSURelatedEntitiesTransporter {
    private final User user;
    private final List<PlanYearSubject> planYearSubjects;
    private final Status status;
}
