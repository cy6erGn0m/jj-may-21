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

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UsersDAOTest {
    @Autowired
    private EntityManager manager;

    @Autowired
    private UsersDAO users;

    @Autowired
    private UsersCustomSort sortedUsers;

    @Test
    public void createAndFindById() {
        User createdUser = users.create("login1", "pass");
        assertNotNull(createdUser);

        assertEquals(createdUser, manager.find(User.class, createdUser.getId()));
        assertEquals(createdUser, users.findById(createdUser.getId()));

        assertNull(users.findById(687875786));
    }

    @Test
    public void findByLoginExisting() {
        User createdUser = users.create("login1", "pass");

        assertEquals(createdUser, users.findByLogin("login1"));
    }

    @Test
    public void findByLoginNonExisting() {
        users.create("login1", "pass");
        assertNull(users.findByLogin("login2"));
    }

    @Test
    public void findByLoginAndPassword() {
        User createdUser = users.create("login1", "pass");

        assertEquals(createdUser, users.findByLoginAndPassword("login1", "pass"));
        assertNull(users.findByLoginAndPassword("login2", "pass"));
        assertNull(users.findByLoginAndPassword("login1", "pass00"));
    }

    @Test
    public void findByGroupName() {
        User createdUser = users.create("login1", "pass");

        Group group = new Group("my_group");
        manager.getTransaction().begin();
        manager.persist(group);
        createdUser.setGroup(group);
        manager.getTransaction().commit();

        assertEquals(singletonList(createdUser), users.findByGroupName("my_group"));
        assertEquals(emptyList(), users.findByGroupName("non-existing-group"));
    }

    @Test
    public void testSortedBy() {
        User first = users.create("login1", "pass2");
        User second = users.create("login2", "pass1");

        assertEquals(Arrays.asList(first, second), sortedUsers.findAllSortedBy("login"));
        assertEquals(Arrays.asList(second, first), sortedUsers.findAllSortedBy("password"));

        try {
            sortedUsers.findAllSortedBy("-- wrong column name");
            fail("Sorting by non-existing column shouldn't work");
        } catch (IllegalArgumentException expected) {
        }
    }

    @Test
    public void testSortedByWrongColumn() {
        try {
            sortedUsers.findAllSortedBy("-- wrong column name");
            fail("The sort should fail");
        } catch (IllegalArgumentException expected) {
        }
    }

    @Test
    public void testCount() {
        assertEquals(0, users.count());

        users.create("1", "1");
        assertEquals(1, users.count());

        users.create("12", "12");
        assertEquals(2, users.count());
    }
}
