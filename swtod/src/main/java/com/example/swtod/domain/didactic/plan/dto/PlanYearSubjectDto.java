package com.example.swtod.domain.didactic.plan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PlanYearSubjectDto {
    private String facultyName;
    private Integer year;
    private String fieldOfStudiesName;
    private String typeOfStudiesName;
    private String subjectName;

    private Integer weeksPerSemester;
    private Integer lectureHoursNumberPerWeek;
    private Integer exerciseHoursNumberPerWeek;
    private Integer laboratoryHoursNumberPerWeek;
    private Integer projectHoursNumberPerWeek;
    private Integer seminaryHoursNumberPerWeek;
    private Integer numberOfStudents;

    private Integer groupsPerLecture;
    private Integer lectureHoursNumber;

    private Integer groupsPerExercise;
    private Integer exerciseHoursNumber;

    private Integer groupsPerLaboratory;
    private Integer laboratoryHoursNumber;

    private Integer groupsPerProject;
    private Integer projectHoursNumber;

    private Integer groupsPerSeminary;
    private Integer seminaryHoursNumber;

    private String semesterType;

    private Integer hoursTotal;

    public PlanYearSubjectDto(List<String> records, String facultyName) {
        for(String record: records) {
            System.out.println(record);
        }

        this.facultyName = facultyName;
        this.year = Integer.parseInt(records.get(0));
        this.fieldOfStudiesName = records.get(1);
        this.typeOfStudiesName = records.get(2);
        this.subjectName = records.get(3);
        this.weeksPerSemester = Integer.parseInt(records.get(4));
        this.lectureHoursNumberPerWeek = Integer.parseInt(records.get(5));
        this.exerciseHoursNumberPerWeek = Integer.parseInt(records.get(6));
        this.laboratoryHoursNumberPerWeek = Integer.parseInt(records.get(7));
        this.projectHoursNumberPerWeek = Integer.parseInt(records.get(8));
        this.seminaryHoursNumberPerWeek = Integer.parseInt(records.get(9));
        this.numberOfStudents = Integer.parseInt(records.get(10));
        this.groupsPerLecture = Integer.parseInt(records.get(11));
        this.lectureHoursNumber = Integer.parseInt(records.get(12));
        this.groupsPerExercise = Integer.parseInt(records.get(13));
        this.exerciseHoursNumber = Integer.parseInt(records.get(14));
        this.groupsPerLaboratory = Integer.parseInt(records.get(15));
        this.laboratoryHoursNumber = Integer.parseInt(records.get(16));
        this.groupsPerProject = Integer.parseInt(records.get(17));
        this.projectHoursNumber = Integer.parseInt(records.get(18));
        this.groupsPerSeminary = Integer.parseInt(records.get(19));
        this.seminaryHoursNumber = Integer.parseInt(records.get(20));
        this.semesterType = records.get(21);
        this.hoursTotal = Integer.parseInt(records.get(22));
    }
}
