package ru.levelp.jj.web;

public class UserSession {
    private int userId;
    private String login;

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
}
