package com.example.swtod.domain.common.studies.type;

import com.example.swtod.domain.didactic.plan.PlanYearSubject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "studies_type")
@AllArgsConstructor
@NoArgsConstructor
public class StudiesType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "studiesType", cascade = CascadeType.ALL)
    private List<PlanYearSubject> planYearSubject;

    public StudiesType(String name) {
        this.name = name;
    }
}
