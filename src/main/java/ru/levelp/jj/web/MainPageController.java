package ru.levelp.jj.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.levelp.jj.dao.TransactionsDAO;
import ru.levelp.jj.model.Transaction;

import java.util.List;

@Controller
@RequestMapping(path = "/")
public class MainPageController {
    @Autowired
    private TransactionsDAO transactions;

    @GetMapping
    public String index(Model model, @RequestParam(required = false, defaultValue = "10") int count) {
        List<Transaction> lastTransactions = transactions.findLast(count);

        model.addAttribute("lastTransactions", lastTransactions);

        return "index";
    }
}
