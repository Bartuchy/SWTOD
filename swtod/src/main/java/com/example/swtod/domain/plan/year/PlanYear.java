package com.example.swtod.domain.plan.year;

import com.example.swtod.domain.didactic.plan.PlanYearSubject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "plan_year")
@AllArgsConstructor
@NoArgsConstructor
public class PlanYear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(nullable = false)
    private int year;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "planYear")
    private List<PlanYearSubject> planYearSubject;

    public PlanYear(String name, int year) {
        this.name = name;
        this.year = year;
        this.description = "";
    }
}
