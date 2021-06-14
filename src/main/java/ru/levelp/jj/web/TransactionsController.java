package ru.levelp.jj.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.levelp.jj.dao.TransactionsDAO;
import ru.levelp.jj.dao.UsersDAO;
import ru.levelp.jj.model.User;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
public class TransactionsController {
    @Autowired
    private TransactionsDAO transactions;

    @Autowired
    private UsersDAO users;

    @GetMapping("/transactions/generate")
    public String createExampleTransactions() {
        List<User> foundUsers = users.findAllSortedBy("login");
        if (foundUsers.size() < 2) {
            return "redirect:/";
        }

        Random random = new Random();

        for (int i = 0; i < 5; ++i) {
            transactions.create(new Date(), random.nextInt(999) + 1,
                    foundUsers.get(0),
                    foundUsers.get(1)
            );
        }

        return "redirect:/";
    }
}
