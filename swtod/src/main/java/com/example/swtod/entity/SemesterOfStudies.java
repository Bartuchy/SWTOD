package com.example.swtod.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "semester_of_studies")
@AllArgsConstructor
@NoArgsConstructor
public class SemesterOfStudies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Integer number;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "semesterOfStudies")
    private List<Subject> subjects;
}
