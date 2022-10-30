package com.example.swtod.domain.faculty;

import com.example.swtod.domain.didactic.plan.PlanYearSubject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "faculty")
@AllArgsConstructor
@NoArgsConstructor
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "faculty", cascade = CascadeType.ALL)
    private List<PlanYearSubject> planYearSubject;

    public Faculty(String name) {
        this.name = name;
    }
}
