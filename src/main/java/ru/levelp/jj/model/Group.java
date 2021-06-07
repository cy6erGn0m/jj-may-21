package ru.levelp.jj.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "groups")
@NamedQueries(
        @NamedQuery(
                name = "findByName",
                query = "select g from Group g where g.name = :groupName"
        )
)
public class Group {
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "group")
//    @JoinTable(name = "users_to_groups",
//            joinColumns = @JoinColumn(name = "groups_fk"),
//            inverseJoinColumns = @JoinColumn(name = "users_fk")
//    )
    private List<User> users;

    public Group(String name) {
        this.name = name;
    }

    public Group() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
