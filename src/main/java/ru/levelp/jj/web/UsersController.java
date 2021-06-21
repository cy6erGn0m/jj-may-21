package ru.levelp.jj.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.levelp.jj.dao.UsersDAO;
import ru.levelp.jj.model.User;

import javax.validation.Valid;

@Controller
@SessionAttributes("userSession")
public class UsersController {
    @Autowired
    private UsersDAO users;

    @GetMapping("/register")
    public String showRegisterForm(
            @ModelAttribute("form") RegistrationForm form) {
        return "register";
    }

    @PostMapping("/register")
    public String handleRegister(
            @ModelAttribute("form")
            @Valid
            RegistrationForm form,
            BindingResult result
    ) {
        if (!form.getPassword().equals(form.getPasswordConfirmation())) {
            result.addError(new FieldError(
                    "form",
                    "passwordConfirmation",
                    "Password and confirmation should match"
            ));
        }

        if (result.hasErrors()) {
            return "register";
        }

        users.create(form.getLogin(), form.getPassword());
        return "redirect:/";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLoginForm(
            @RequestParam String login,
            @RequestParam String password,
            UserSession userSession
    ) {
        User user = users.findByLoginAndPassword(login, password);
        if (user != null) {
            userSession.setUserId(user.getId());
            userSession.setLogin(user.getLogin());
            return "redirect:/";
        }

        userSession.clear();
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String handleLogout(UserSession userSession) {
        userSession.clear();
        return "redirect:/";
    }

    @ModelAttribute("form")
    public RegistrationForm createDefault() {
        return new RegistrationForm();
    }
}
