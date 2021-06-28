package ru.levelp.jj.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String login;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column
    private double balance;

    @ManyToOne
    @JoinColumn(name = "group_fk")
    private Group group;

    @OneToMany(mappedBy = "sender")
    @JsonIgnore
    private List<Transaction> transactionsSending;

    @OneToMany(mappedBy = "recipient")
    @JsonIgnore
    private List<Transaction> transactionsRecieving;

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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", group=" + group +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Double.compare(user.balance, balance) == 0 && login.equals(user.login) && password.equals(user.password) && Objects.equals(group, user.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, balance, group);
    }
}
