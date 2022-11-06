package com.example.swtod.domain.didactic.plan;

import com.example.swtod.domain.didactic.plan.dto.PlanYearSubjectRecordDto;
import com.example.swtod.domain.didactic.plan.management.PlanYearSubjectCsvProcessor;
import com.example.swtod.domain.didactic.plan.management.PlanYearSubjectMapper;
import com.example.swtod.domain.plan.year.PlanYearService;
import com.example.swtod.domain.subject.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanYearSubjectService {
    private final PlanYearSubjectRepository planYearSubjectRepository;
    private final PlanYearService planYearService;
    private final SubjectService subjectService;

    private final PlanYearSubjectCsvProcessor processor;
    private final PlanYearSubjectMapper mapper;

    public void savePlanYearSubjects(MultipartFile csvFile, String facultyName) throws IOException {
        List<PlanYearSubject> planYearSubjects = processor.processPlanYearSubjectCsv(csvFile, facultyName);
        planYearSubjectRepository.saveAll(planYearSubjects);
    }

    @Transactional
    public void removeDidacticPlan() {
        planYearSubjectRepository.deleteAll();
        planYearService.removeAllData();
        subjectService.removeAllData();
    }

    @Transactional
    public void removeDidacticPlanSingle(Long subjectId) {
        List<PlanYearSubject> planYearSubjects = planYearSubjectRepository
                .findPlanYearSubjectBySubjectId(subjectId);

        planYearSubjects.forEach(planYearSubject -> planYearSubjectRepository
                .deletePlanYearSubjectById(planYearSubject.getId()));

        if (planYearSubjects.size() > 0)
            removeSingleRelatedEntities(planYearSubjects.get(0));
    }

    public List<PlanYearSubjectRecordDto> getDidacticPlan() {
        List<PlanYearSubject> planYearSubjects = planYearSubjectRepository.findAll();
        return mapper.mapEntitiesToDtos(planYearSubjects);
    }

    private void removeSingleRelatedEntities(PlanYearSubject planYearSubject) {
        Long planYearId = planYearSubject.getPlanYear().getId();
        Long subjectId = planYearSubject.getSubject().getId();

        planYearService.removeById(planYearId);
        subjectService.removeById(subjectId);
    }
}
