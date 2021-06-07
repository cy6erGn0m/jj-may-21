package ru.levelp.jj.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.levelp.jj.model.Group;
import ru.levelp.jj.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class GroupDAOTest {
    private EntityManagerFactory factory;
    private EntityManager manager;
    private GroupDAO groups;
    private UsersDAO users;

    @Before
    public void setUp() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
        groups = new GroupDAO(manager);
        users = new UsersDAO(manager);
    }

    @After
    public void tearDown() {
        if (manager != null) {
            manager.close();
        }
        if (factory != null) {
            factory.close();
        }
    }

    @Test
    public void create() {
        Group createdGroup = groups.create("test-group");
        assertNotNull(createdGroup);
        assertEquals(createdGroup, manager.find(Group.class, createdGroup.getId()));
    }

    @Test
    public void findByName() {
        Group createdGroup = groups.create("test-group");

        assertEquals(createdGroup, groups.findByName("test-group"));
        assertNull(groups.findByName("some-non-existing-group"));
    }

    @Test
    public void testFindBigGroups() {
        assertEquals(Collections.emptyList(), groups.findBigGroups(1));

        User a = users.create("user1", "pass");
        User b = users.create("user2", "pass");
        User c = users.create("user3", "pass");
        Group big = groups.create("big");
        Group small = groups.create("small");

        manager.getTransaction().begin();
        a.setGroup(big);
        b.setGroup(big);
        c.setGroup(small);
        manager.getTransaction().commit();

        assertEquals(Collections.singletonList(big), groups.findBigGroups(2));
        assertEquals(Arrays.asList(big, small), groups.findBigGroups(1));
    }
}