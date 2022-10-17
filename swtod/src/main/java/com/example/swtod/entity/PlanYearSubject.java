package com.example.swtod.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "plan_year_subject")
@AllArgsConstructor
@NoArgsConstructor
public class PlanYearSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "groups_number", nullable = false)
    private int groupsNumber;

    @Column(name = "weeks_number", nullable = false)
    private int weeksNumber;

    @Column(name = "hours_per_week", nullable = false)
    private double hoursPerWeek;

    @Column(name = "students_number", nullable = false)
    private int studentsNumber;

    @Column(nullable = false)
    private char semester;

    @Column(name = "semester_number", nullable = false)
    private int semesterNumber;

    @ManyToOne
    private ClassesType classesType;

    @ManyToOne
    private StudiesType studiesType;

    @ManyToOne
    private PlanYear planYear;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Faculty faculty;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "planYearSubject")
    private List<PlanYearSubjectUser> planYearSubjectUsers;
}
