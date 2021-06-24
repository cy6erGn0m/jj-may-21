package ru.levelp.jj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.levelp.jj.model.User;

import java.util.List;

@Repository
public interface UsersDAO extends JpaRepository<User, Integer> {

    User findByLogin(String login);

    User findByLoginAndPassword(String login, String password);

    @Query("select u from User u where u.group.name = :groupName")
    List<User> findByGroupName(@Param("groupName") String groupName);

    @Transactional
    default User create(String login, String password) {
        User user = new User(login, password);
        save(user);
        return user;
    }

    @Transactional
    default void credit(User user, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount should be positive");
        }
        user.setBalance(user.getBalance() + amount);
    }

    @Transactional
    default void debit(User user, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount should be positive");
        }
        if (user.getBalance() < amount) {
            throw new IllegalStateException("Not enough money to debit");
        }
        user.setBalance(user.getBalance() - amount);
    }
}
