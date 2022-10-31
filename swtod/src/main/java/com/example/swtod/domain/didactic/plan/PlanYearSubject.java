package com.example.swtod.domain.didactic.plan;

import com.example.swtod.domain.classes.type.ClassesType;
import com.example.swtod.domain.entity.*;
import com.example.swtod.domain.faculty.Faculty;
import com.example.swtod.domain.plan.year.PlanYear;
import com.example.swtod.domain.studies.type.StudiesType;
import com.example.swtod.domain.subject.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "planYearSubject", cascade = CascadeType.ALL)
    private List<PlanYearSubjectUser> planYearSubjectUsers;

    public PlanYearSubject(
            int groupsNumber, int weeksNumber, double hoursPerWeek, int studentsNumber,
            char semester, int semesterNumber, ClassesType classesType,
            StudiesType studiesType, PlanYear planYear, Subject subject, Faculty faculty) {
        this.groupsNumber = groupsNumber;
        this.weeksNumber = weeksNumber;
        this.hoursPerWeek = hoursPerWeek;
        this.studentsNumber = studentsNumber;
        this.semester = semester;
        this.semesterNumber = semesterNumber;
        this.classesType = classesType;
        this.studiesType = studiesType;
        this.planYear = planYear;
        this.subject = subject;
        this.faculty = faculty;
    }
}
