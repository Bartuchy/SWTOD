package com.example.swtod.domain.teaching.staff;

import com.example.swtod.domain.didactic.plan.PlanYearSubject;
import com.example.swtod.domain.common.status.Status;
import com.example.swtod.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "plan_year_subject_user")
@AllArgsConstructor
@NoArgsConstructor
public class PlanYearSubjectUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "groups_number")
    private Integer groupsNumber;

    @Column(name = "comment_content")
    private String commentContent;

    @ManyToOne
    private PlanYearSubject planYearSubject;

    @ManyToOne
    private User user;

    @ManyToOne
    private Status status;

    public PlanYearSubjectUser(Integer groupsNumber, String commentContent, PlanYearSubject planYearSubject, User user, Status status) {
        this.groupsNumber = groupsNumber;
        this.commentContent = commentContent;
        this.planYearSubject = planYearSubject;
        this.user = user;
        this.status = status;
    }
}
