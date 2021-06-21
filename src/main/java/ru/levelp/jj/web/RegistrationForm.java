package ru.levelp.jj.web;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegistrationForm {
    @Size(min = 4, max = 10)
    @Pattern(regexp = "[a-zA-Z0-9_.-]{4,10}",
            message = "Login should consist of letters, digits, underscore, " +
                    "dot or hyphen")
    private String login;

    @Size(min = 4, max = 10)
    private String password;

    private String passwordConfirmation;

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

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}
