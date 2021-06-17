package ru.levelp.jj.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.levelp.jj.dao.UsersDAO;
import ru.levelp.jj.model.User;

@Controller
@SessionAttributes("userSession")
public class UsersController {
    @Autowired
    private UsersDAO users;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String handleRegister(
            @RequestParam String login,
            @RequestParam String password
    ) {
        users.create(login, password);
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

        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String handleLogout(UserSession userSession) {
        userSession.clear();
        return "redirect:/";
    }
}
