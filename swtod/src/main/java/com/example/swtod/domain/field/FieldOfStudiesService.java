package com.example.swtod.domain.field;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FieldOfStudiesService {
    private final FieldOfStudiesRepository fieldOfStudiesRepository;

    public FieldOfStudies getFieldOfStudiesByNameAndDegree(String name, int degreeNumber) {
        return fieldOfStudiesRepository
                .findFieldOfStudiesByNameAndDegree(name, degreeNumber)
                .orElseThrow();
    }
}
