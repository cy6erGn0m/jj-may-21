package ru.levelp.jj;

import org.junit.Assert;
import org.junit.Test;
import ru.levelp.jj.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.assertNotNull;

public class SmokeTest {
    @Test
    public void createUserTest() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        EntityManager entityManager = factory.createEntityManager();

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

        entityManager.close();
        factory.close();
    }
}
