package ru.levelp.jj.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "group")
    private List<User> users;
}
