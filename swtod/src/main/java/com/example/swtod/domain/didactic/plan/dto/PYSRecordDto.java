package com.example.swtod.domain.didactic.plan.dto;

import lombok.*;

import java.util.List;
import java.util.Map;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PYSRecordDto {
    private Map<String, Long> classesTypeNamesPysIds;
    private Long id;
    private Long subjectId;
    private String facultyName;
    private Integer year;
    private String fieldOfStudiesName;
    private String typeOfStudiesName;
    private String subjectName;

    private Integer weeksPerSemester;
    private Double lectureHoursNumberPerWeek;
    private Double exerciseHoursNumberPerWeek;
    private Double laboratoryHoursNumberPerWeek;
    private Double projectHoursNumberPerWeek;
    private Double seminaryHoursNumberPerWeek;
    private Integer numberOfStudents;

    private Integer groupsPerLecture;
    private Double lectureHoursNumber;

    private Integer groupsPerExercise;
    private Double exerciseHoursNumber;

    private Integer groupsPerLaboratory;
    private Double laboratoryHoursNumber;

    private Integer groupsPerProject;
    private Double projectHoursNumber;

    private Integer groupsPerSeminary;
    private Double seminaryHoursNumber;

    private String semesterType;

    private Double hoursTotal;

    public PYSRecordDto(List<String> records, String facultyName) {
        this.facultyName = facultyName;
        this.year = Integer.parseInt(records.get(0));
        this.fieldOfStudiesName = records.get(1);
        this.typeOfStudiesName = records.get(2);
        this.subjectName = records.get(3);
        this.weeksPerSemester = Integer.parseInt(records.get(4));
        this.lectureHoursNumberPerWeek = Double.parseDouble(records.get(5));
        this.exerciseHoursNumberPerWeek = Double.parseDouble(records.get(6));
        this.laboratoryHoursNumberPerWeek = Double.parseDouble(records.get(7));
        this.projectHoursNumberPerWeek = Double.parseDouble(records.get(8));
        this.seminaryHoursNumberPerWeek = Double.parseDouble(records.get(9));
        this.numberOfStudents = Integer.parseInt(records.get(10));
        this.groupsPerLecture = Integer.parseInt(records.get(11));
        this.lectureHoursNumber = Double.parseDouble(records.get(12));
        this.groupsPerExercise = Integer.parseInt(records.get(13));
        this.exerciseHoursNumber = Double.parseDouble(records.get(14));
        this.groupsPerLaboratory = Integer.parseInt(records.get(15));
        this.laboratoryHoursNumber = Double.parseDouble(records.get(16));
        this.groupsPerProject = Integer.parseInt(records.get(17));
        this.projectHoursNumber = Double.parseDouble(records.get(18));
        this.groupsPerSeminary = Integer.parseInt(records.get(19));
        this.seminaryHoursNumber = Double.parseDouble(records.get(20));
        this.semesterType = records.get(21);
        this.hoursTotal = Double.parseDouble(records.get(22));
    }

    public void addClassTypeNamePysId(String classTypeName, Long pysId) {
        classesTypeNamesPysIds.put(classTypeName, pysId);
    }


}
