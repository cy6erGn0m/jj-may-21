package ru.levelp.jj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.levelp.jj.configs.AppConfig;
import ru.levelp.jj.model.User;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SmokeTest {
    @Autowired
    private EntityManager entityManager;

    @Test
    public void createUserTest() {
        User user = new User("test", "pass");
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();

        User foundUser = entityManager.find(User.class, user.getId());
        assertNotNull(foundUser);

        User foundByQuery = entityManager.createQuery("select u from User u where u.id = :id", User.class)
                .setParameter("id", user.getId())
                .getSingleResult();
        assertNotNull(foundByQuery);
    }
}
