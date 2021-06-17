package ru.levelp.jj.dao;

import ru.levelp.jj.model.Transaction;
import ru.levelp.jj.model.User;

import java.util.Date;
import java.util.List;

public interface TransactionsDAO {
    Transaction create(Date time, double amount, User sender, User recipient);

    List<Transaction> findByUser(User user);

    List<Transaction> findLast(int count);
}
