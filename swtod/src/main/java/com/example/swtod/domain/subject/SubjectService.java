package com.example.swtod.domain.subject;

import com.example.swtod.domain.field.FieldOfStudies;
import com.example.swtod.domain.field.FieldOfStudiesService;
import com.example.swtod.domain.semester.SemesterOfStudies;
import com.example.swtod.domain.semester.SemesterOfStudiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final FieldOfStudiesService fieldOfStudiesService;
    private final SemesterOfStudiesService semesterOfStudiesService;

    public Subject saveNewSubject(String subjectName, String fieldName, int degreeNumber, String semesterType, int semesterNumber) {
        FieldOfStudies field = fieldOfStudiesService.getFieldOfStudiesByNameAndDegree(fieldName, degreeNumber);
        SemesterOfStudies semesterOfStudies = semesterOfStudiesService.getSemesterOfStudiesByNameAndNumber(semesterType, semesterNumber);

        return subjectRepository.save(new Subject(
                subjectName,
                field,
                semesterOfStudies));
    }
}
