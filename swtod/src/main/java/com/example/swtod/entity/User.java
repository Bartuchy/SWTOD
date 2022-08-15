package com.example.swtod.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

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
    @ManyToOne
    private Position position;

    public User(String username, String name, String surname, String password, LocalDate dob, String title, Position position) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.dob = dob;
        this.title = title;
        this.position = position;

        this.is_active = true;
        this.pensum = 0;
        this.is_admin = false;
    }
}
