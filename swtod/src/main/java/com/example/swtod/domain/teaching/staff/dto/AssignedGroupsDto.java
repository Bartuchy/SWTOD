package com.example.swtod.domain.teaching.staff.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AssignedGroupsDto {
    private int lectureGroupsNumber;
    private int exerciseGroupsNumber;
    private int laboratoryGroupsNumber;
    private int projectGroupsNumber;
    private int seminaryGroupsNumber;

    public AssignedGroupsDto(PYSURecordDto recordDto) {
        this.lectureGroupsNumber = recordDto.getGroupsPerLecture();
        this.exerciseGroupsNumber = recordDto.getGroupsPerExercise();
        this.laboratoryGroupsNumber = recordDto.getGroupsPerLaboratory();
        this.projectGroupsNumber = recordDto.getGroupsPerProject();
        this.seminaryGroupsNumber = recordDto.getGroupsPerSeminary();

    }
}
