package ru.levelp.jj.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.levelp.jj.dao.UsersDAO;

@Controller
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
}
