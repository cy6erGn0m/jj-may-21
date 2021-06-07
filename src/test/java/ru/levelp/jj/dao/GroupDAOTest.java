package ru.levelp.jj.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.levelp.jj.model.Group;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

public class GroupDAOTest {
    private EntityManagerFactory factory;
    private EntityManager manager;
    private GroupDAO groups;

    @Before
    public void setUp() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
        groups = new GroupDAO(manager);
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
}