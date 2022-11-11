package com.example.swtod.domain.common.classes.type;

import com.example.swtod.domain.didactic.plan.PlanYearSubject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "classes_type")
@AllArgsConstructor
@NoArgsConstructor
public class ClassesType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "classesType", cascade = CascadeType.ALL)
    private List<PlanYearSubject> planYearSubject;

    public ClassesType(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
