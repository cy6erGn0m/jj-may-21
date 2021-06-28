package ru.levelp.jj.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.levelp.jj.model.Transaction;
import ru.levelp.jj.model.User;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionsDAO extends JpaRepository<Transaction, Long> {
    @Query("from Transaction where sender = :user or recipient = :user")
    List<Transaction> findByUser(User user);

    @Query("from Transaction order by time desc")
    List<Transaction> findLast(Pageable paging);

    default List<Transaction> findLast(int count) {
        return findLast(PageRequest.ofSize(count).first());
    }

    @Transactional
    default Transaction create(Date time, double amount, User sender, User recipient) {
        Transaction tx = new Transaction(time, amount, sender, recipient);
        save(tx);
        return tx;
    }
}
