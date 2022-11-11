package com.example.swtod.domain.teaching.staff.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AssignedGroupsDto {
    private final int lectureGroupsNumber;
    private final int exerciseGroupsNumber;
    private final int laboratoryGroupsNumber;
    private final int projectGroupsNumber;
    private final int SeminaryGroupsNumber;
}
