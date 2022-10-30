package com.example.swtod.domain.field;

import com.example.swtod.domain.subject.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "field_of_studies")
@AllArgsConstructor
@NoArgsConstructor
public class FieldOfStudies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Integer degree;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "fieldOfStudies", cascade = CascadeType.ALL)
    private List<Subject> subjects;

    public FieldOfStudies(String name, Integer degree) {
        this.name = name;
        this.degree = degree;
    }
}
