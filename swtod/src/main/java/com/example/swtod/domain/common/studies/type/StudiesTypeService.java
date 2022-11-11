package com.example.swtod.domain.common.studies.type;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudiesTypeService {
    private final StudiesTypeRepository studiesTypeRepository;

    public StudiesType getStudiesTypeByName(String name) {
        return studiesTypeRepository
                .findStudiesTypeByName(name)
                .orElseThrow();
    }
}
