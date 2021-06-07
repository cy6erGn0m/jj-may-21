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
//    @JoinTable(name = "users_to_groups",
//            joinColumns = @JoinColumn(name = "groups_fk"),
//            inverseJoinColumns = @JoinColumn(name = "users_fk")
//    )
    private List<User> users;
}
