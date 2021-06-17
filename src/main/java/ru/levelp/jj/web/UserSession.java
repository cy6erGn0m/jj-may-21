package ru.levelp.jj.web;

import java.util.Objects;

public class UserSession {
    private int userId;
    private String login;

    public UserSession(int userId, String login) {
        this.userId = userId;
        this.login = login;
    }

    public UserSession() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isLoggedIn() {
        return userId > 0;
    }

    public void clear() {
        userId = 0;
        login = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSession that = (UserSession) o;
        return userId == that.userId && Objects.equals(login, that.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, login);
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                '}';
    }
}
