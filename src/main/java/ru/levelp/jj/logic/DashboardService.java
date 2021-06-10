package ru.levelp.jj.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.levelp.jj.dao.TransactionsDAO;
import ru.levelp.jj.dao.UsersDAO;
import ru.levelp.jj.model.Transaction;
import ru.levelp.jj.model.User;

import java.util.List;

@Service
public class DashboardService {
    @Autowired
    private UsersDAO users;

    @Autowired
    private TransactionsDAO transactions;

    public List<Transaction> findByUserId(int userId) {
        User user = users.findById(userId);
        if (user == null) {
            throw new IllegalStateException("User " + userId + " not found");
        }

        return transactions.findByUser(user);
    }
}
