package com.example.swtod.domain.didactic.plan.management.transfer;

import com.example.swtod.domain.faculty.Faculty;
import com.example.swtod.domain.plan.year.PlanYear;
import com.example.swtod.domain.studies.type.StudiesType;
import com.example.swtod.domain.subject.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PYSRelatedEntitiesTransporter {
    private final StudiesType studiesType;
    private final PlanYear planYear;
    private final Subject subject;
    private final Faculty faculty;
}
