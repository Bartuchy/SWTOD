package com.example.swtod.domain.didactic.plan;

import com.example.swtod.domain.didactic.plan.management.PlanYearSubjectCsvProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanYearSubjectService {
    private final PlanYearSubjectRepository planYearSubjectRepository;

    private final PlanYearSubjectCsvProcessor processor;

    public void savePlanYearSubjects(MultipartFile csvFile, String facultyName) throws IOException {
        List<PlanYearSubject> planYearSubjects = processor.processPlanYearSubjectCsv(csvFile, facultyName);
        planYearSubjectRepository.saveAll(planYearSubjects);
    }
}
