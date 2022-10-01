package com.example.swtod.entity;

import com.example.swtod.user.User;
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


}
