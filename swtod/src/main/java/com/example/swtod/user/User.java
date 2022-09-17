package com.example.swtod.user;

import com.example.swtod.entity.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

import static java.util.Collections.emptySet;

@Entity(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String password;
    private boolean is_active;
    private LocalDate dob;
    private int pensum;
    private String title;
    private boolean is_admin;

    @OneToMany
    private Set<Position> positions;

    public User(String title, String username, String name, String surname,
                LocalDate dob, boolean is_admin, String password) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.is_active = true;
        this.dob = dob;
        this.pensum = 0;
        this.title = title;
        this.is_admin = is_admin;
        this.positions = emptySet();
    }
}
