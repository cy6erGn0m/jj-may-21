package ru.levelp.jj.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.levelp.jj.model.Transaction;
import ru.levelp.jj.model.User;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Repository
public class TransactionsDAO {
    @Autowired
    private EntityManager manager;

    public Transaction create(Date time, double amount, User sender, User recipient) {
        Transaction tx = new Transaction(time, amount, sender, recipient);
        manager.getTransaction().begin();
        try {
            manager.persist(tx);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }

        return tx;
    }

    public List<Transaction> findByUser(User user) {
        return manager.createQuery(
                "from Transaction " +
                        "where sender.id = :userId or recipient.id = :userId", Transaction.class)
                .setParameter("userId", user.getId())
                .getResultList();
    }
}
