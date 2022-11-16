package com.example.swtod.domain.teaching.staff.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PYSURecordDto {
    private Long userId;

    private String userNameSurname;
    private String facultyName;
    private String subjectName;
    private String fieldOfStudiesName;
    private String studiesTypeName;
    private Integer year;
    private Integer semesterNumber;
    private Integer noWeeks;

    private Double lectureHoursPerWeek;
    private Integer groupsPerLecture;

    private Double exerciseHoursPerWeek;
    private Integer groupsPerExercise;

    private Double laboratoryHoursPerWeek;
    private Integer groupsPerLaboratory;

    private Double projectHoursPerWeek;
    private Integer groupsPerProject;

    private Double seminaryHoursPerWeek;
    private Integer groupsPerSeminary;

    private String statusName;
}
