package ru.levelp.jj.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import ru.levelp.jj.TestConfig;
import ru.levelp.jj.model.Group;
import ru.levelp.jj.model.User;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GroupDAOTest {
    @Autowired
    private EntityManager manager;

    @Autowired
    private GroupDAO groups;

    @Autowired
    private UsersDAO users;

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