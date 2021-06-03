package ru.levelp.jj.model;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, unique = true, length = 50)
    private String login;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    private Group group;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User() {
    }
}
