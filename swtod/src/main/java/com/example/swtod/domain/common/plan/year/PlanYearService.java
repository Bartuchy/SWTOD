package com.example.swtod.domain.common.plan.year;

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

    public void removeAllData() {
        planYearRepository.deleteAll();
    }

    public void removeById(Long id) {
        planYearRepository.deleteById(id);
    }
}
