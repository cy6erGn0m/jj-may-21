package ru.levelp.jj.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.levelp.jj.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UsersDAO {
    @Autowired
    private EntityManager manager;

    public User create(String login, String password) {
        User user = new User(login, password);

        manager.getTransaction().begin();
        try {
            manager.persist(user);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }

        return user;
    }

    public User findById(int id) {
        return manager.find(User.class, id);
    }

    public User findByLogin(String login) {
        try {
            return manager.createQuery("select u from User u where u.login = :login_to_search", User.class)
                    .setParameter("login_to_search", login)
                    .getSingleResult();
        } catch (NoResultException notFound) {
            return null;
        }
    }

    public User findByLoginAndPassword(String login, String password) {
        try {
            return manager.createQuery("select u from User u where " +
                    "u.login = :login_to_search and u.password = :pass", User.class)
                    .setParameter("login_to_search", login)
                    .setParameter("pass", password)
                    .getSingleResult();
        } catch (NoResultException notFound) {
            return null;
        }
    }

    public List<User> findByGroupName(String groupName) {
        return manager.createQuery("select u from User u where u.group.name = :groupName", User.class)
                .setParameter("groupName", groupName)
                .getResultList();
    }

    public int count() {
        return manager.createQuery("select count(u) from User u", Number.class).getSingleResult().intValue();
    }

    public List<User> findAllSortedBy(String columnName) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);

        query.orderBy(builder.asc(root.get(columnName)));

        return manager.createQuery(query).getResultList();
    }
}
