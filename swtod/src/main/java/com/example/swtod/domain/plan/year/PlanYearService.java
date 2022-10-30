package com.example.swtod.domain.plan.year;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanYearService {
    private final PlanYearRepository planYearRepository;

    public PlanYear saveNewPlanYear(String name, int year) {
        PlanYear planYear = new PlanYear(name, year);
        return planYearRepository.save(planYear);
    }
}
