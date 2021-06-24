package ru.levelp.jj.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.levelp.jj.model.Transaction;
import ru.levelp.jj.model.User;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Repository
public class TransactionsDAOImpl implements TransactionsDAO {
    @Autowired
    private EntityManager manager;

    @Override
    @Transactional
    public Transaction create(Date time, double amount, User sender, User recipient) {
        Transaction tx = new Transaction(time, amount, sender, recipient);
        manager.persist(tx);
        return tx;
    }

    @Override
    public List<Transaction> findByUser(User user) {
        return manager.createQuery(
                "from Transaction " +
                        "where sender.id = :userId or recipient.id = :userId", Transaction.class)
                .setParameter("userId", user.getId())
                .getResultList();
    }

    @Override
    public List<Transaction> findLast(int count) {
        return manager.createQuery(
                "from Transaction order by time desc",
                Transaction.class
        ).setMaxResults(count).getResultList();
    }
}

//class TransactionsDAOGenerated extends TransactionsDAOImpl {
//    @Override
//    public Transaction create(Date time, double amount, User sender, User recipient) {
//        boolean started;
//        // if (!hasTransaction)
//        // begin + started = true
//        try {
//            return super.create(time, amount, sender, recipient);
//            // if (started) {
//            // commit
//        } catch (Exception cause) {
//            // rollback
//            throw cause;
//        }
//    }
//}
