package com.example.swtod.domain.teaching.staff.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class AssignedGroupsDto {
    private Map<String, Long> classesTypeNamespysuIds;

    private int lectureGroupsNumber;
    private int exerciseGroupsNumber;
    private int laboratoryGroupsNumber;
    private int projectGroupsNumber;
    private int seminaryGroupsNumber;

    public AssignedGroupsDto(PYSURecordDto recordDto) {
        this.classesTypeNamespysuIds = recordDto.getClassesTypeNamespysuIds();
        this.lectureGroupsNumber = recordDto.getGroupsPerLecture();
        this.exerciseGroupsNumber = recordDto.getGroupsPerExercise();
        this.laboratoryGroupsNumber = recordDto.getGroupsPerLaboratory();
        this.projectGroupsNumber = recordDto.getGroupsPerProject();
        this.seminaryGroupsNumber = recordDto.getGroupsPerSeminary();
    }
}
