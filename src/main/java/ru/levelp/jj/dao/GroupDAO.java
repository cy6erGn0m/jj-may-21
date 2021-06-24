package ru.levelp.jj.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.levelp.jj.model.Group;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class GroupDAO {
    private final EntityManager manager;

    @Autowired
    public GroupDAO(EntityManager manager) {
        this.manager = manager;
    }

    @Transactional
    public Group create(String groupName) {
        Group group = new Group(groupName);
        manager.persist(group);
        return group;
    }

    public Group findByName(String groupName) {
        try {
            return manager.createNamedQuery("findByName", Group.class)
                    .setParameter("groupName", groupName)
                    .getSingleResult();
        } catch (NoResultException notFound) {
            return null;
        }
    }

    public List<Group> findBigGroups(int minMembersCount) {
        return manager.createQuery("from Group g where size(g.users) >= :min", Group.class)
                .setParameter("min", minMembersCount)
                .getResultList();
    }
}
