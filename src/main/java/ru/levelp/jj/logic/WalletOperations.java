package ru.levelp.jj.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.levelp.jj.dao.TransactionsDAO;
import ru.levelp.jj.dao.UsersDAO;
import ru.levelp.jj.model.User;

import java.util.Date;

/**
 * @author Sergey Mashkov
 */
@Service
public class WalletOperations {
    @Autowired
    private UsersDAO users;

    @Autowired
    private TransactionsDAO transactions;

    @Transactional
    public void transferMoney(User from, User to, double amount) {
        users.debit(from, amount);
        users.credit(to, amount);
        transactions.create(new Date(), amount, from, to);
    }
}
