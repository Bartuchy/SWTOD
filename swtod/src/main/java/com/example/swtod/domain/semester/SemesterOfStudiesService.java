package com.example.swtod.domain.semester;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SemesterOfStudiesService {
    private final SemesterOfStudiesRepository semesterOfStudiesRepository;

    public SemesterOfStudies getSemesterOfStudiesByNameAndNumber(String semesterType, int semesterNumber) {
        return semesterOfStudiesRepository
                .findSemesterOfStudiesByNameAndNumber(semesterType, semesterNumber)
                .orElseThrow();
    }
}
