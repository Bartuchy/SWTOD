package com.example.swtod.domain.common.faculty;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public Faculty getFacultyByName(String name) {
        return facultyRepository
                .findFacultyByName(name)
                .orElseThrow();
    }
}
