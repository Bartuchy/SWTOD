package com.example.swtod.domain.common.subject;

import com.example.swtod.domain.didactic.plan.PlanYearSubject;
import com.example.swtod.domain.common.field.FieldOfStudies;
import com.example.swtod.domain.common.semester.SemesterOfStudies;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "subject")
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "subject", cascade = CascadeType.ALL)
    private List<PlanYearSubject> planYearSubject;

    @ManyToOne
    private FieldOfStudies fieldOfStudies;

    @ManyToOne
    private SemesterOfStudies semesterOfStudies;

    public Subject(String name, FieldOfStudies fieldOfStudies, SemesterOfStudies semesterOfStudies) {
        this.name = name;
        this.fieldOfStudies = fieldOfStudies;
        this.semesterOfStudies = semesterOfStudies;
        this.description = "";
    }
}
