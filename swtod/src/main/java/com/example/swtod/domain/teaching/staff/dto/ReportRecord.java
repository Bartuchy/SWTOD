package com.example.swtod.domain.teaching.staff.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReportRecord {
    private String facultyName;
    private String subjectName;
    private String fieldOfStudiesName;
    private String studiesTypeName;
    private Integer year;
    private Integer semesterNumber;
    private Integer noWeeks;

    private Double lectureHoursPerWeek;
    private Double lectureHoursPerSem;
    private Integer groupsPerLecture;

    private Double exerciseHoursPerWeek;
    private Double exerciseHoursPerSem;
    private Integer groupsPerExercise;

    private Double laboratoryHoursPerWeek;
    private Double laboratoryHoursPerSem;
    private Integer groupsPerLaboratory;

    private Double projectHoursPerWeek;
    private Double projectHoursPerSem;
    private Integer groupsPerProject;

    private Double seminaryHoursPerWeek;
    private Double seminaryHoursPerSem;
    private Integer groupsPerSeminary;
    private Double totalHoursInYear;

    public ReportRecord(PYSURecordDto pysuRecordDto) {
        this.facultyName = pysuRecordDto.getFacultyName();
        this.subjectName = pysuRecordDto.getSubjectName();
        this.fieldOfStudiesName = pysuRecordDto.getFieldOfStudiesName();
        this.studiesTypeName = pysuRecordDto.getStudiesTypeName();
        this.year = pysuRecordDto.getYear();
        this.semesterNumber = pysuRecordDto.getSemesterNumber();
        this.noWeeks = pysuRecordDto.getNoWeeks();

        this.lectureHoursPerWeek = pysuRecordDto.getLectureHoursPerWeek();
        this.lectureHoursPerSem = pysuRecordDto.getLectureHoursPerWeek() * pysuRecordDto.getNoWeeks();
        this.groupsPerLecture = pysuRecordDto.getGroupsPerLecture();

        this.exerciseHoursPerWeek = pysuRecordDto.getExerciseHoursPerWeek();
        this.exerciseHoursPerSem = pysuRecordDto.getExerciseHoursPerWeek() * pysuRecordDto.getNoWeeks();
        this.groupsPerExercise = pysuRecordDto.getGroupsPerExercise();

        this.laboratoryHoursPerWeek = pysuRecordDto.getLaboratoryHoursPerWeek();
        this.laboratoryHoursPerSem = pysuRecordDto.getLaboratoryHoursPerWeek() * pysuRecordDto.getNoWeeks();
        this.groupsPerLaboratory = pysuRecordDto.getGroupsPerLaboratory();

        this.projectHoursPerWeek = pysuRecordDto.getProjectHoursPerWeek();
        this.projectHoursPerSem = pysuRecordDto.getProjectHoursPerWeek() * pysuRecordDto.getNoWeeks();
        this.groupsPerProject = pysuRecordDto.getGroupsPerProject();

        this.seminaryHoursPerWeek = pysuRecordDto.getSeminaryHoursPerWeek();
        this.seminaryHoursPerSem = pysuRecordDto.getSeminaryHoursPerWeek() * pysuRecordDto.getNoWeeks();
        this.groupsPerSeminary = pysuRecordDto.getGroupsPerLecture();

        this.totalHoursInYear = this.lectureHoursPerSem
                + this.exerciseHoursPerSem
                + this.laboratoryHoursPerSem
                + this.projectHoursPerSem
                + this.seminaryHoursPerSem;
    }
}
