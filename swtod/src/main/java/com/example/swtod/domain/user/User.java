package com.example.swtod.domain.user;

import com.example.swtod.domain.teaching.staff.PlanYearSubjectUser;
import com.example.swtod.domain.common.entity.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", length = 128, nullable = false, unique = true)
    private String username;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String surname;

    @Column(nullable = false)
    private String password;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    private LocalDate dob;

    @Column(nullable = false)
    private int pensum;
    private String title;

    @Column(name = "is_admin", nullable = false)
    private boolean isAdmin;

    @ManyToOne
    private Position position;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<PlanYearSubjectUser> planYearSubjectUsers;

    public User(String title, String username, String name, String surname,
                LocalDate dob, String password, Position position) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.isActive = true;
        this.dob = dob;
        this.pensum = 0;
        this.title = title;
        this.isAdmin = false;
        this.position = position;
    }

    public User(String title, String username, String name, String surname, LocalDate dob, Position position) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.title = title;
        this.position = position;
    }
}
