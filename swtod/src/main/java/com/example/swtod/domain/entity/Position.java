package com.example.swtod.domain.entity;

import com.example.swtod.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "position")
@AllArgsConstructor
@NoArgsConstructor
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "position")
    private List<User> user;

    public Position(Long id) {
        this.id = id;
    }
}
