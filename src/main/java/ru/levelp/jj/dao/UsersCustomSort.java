package ru.levelp.jj.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.levelp.jj.model.User;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UsersCustomSort {
    @Autowired
    private EntityManager manager;

    public List<User> findAllSortedBy(String columnName) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);

        query.orderBy(builder.asc(root.get(columnName)));

        return manager.createQuery(query).getResultList();
    }
}
