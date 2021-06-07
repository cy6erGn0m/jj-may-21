package ru.levelp.jj.model;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String login;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "group_fk")
    private Group group;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
