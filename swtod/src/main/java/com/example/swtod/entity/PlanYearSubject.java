package com.example.swtod.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = "plan_year_subject")
@AllArgsConstructor
@NoArgsConstructor
public class PlanYearSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int groupsNumber;
    private int weeksNumber;
    private double hoursPerWeek;
    private int studentsNumber;
    private char semester;
    private int semesterNumber;


}
