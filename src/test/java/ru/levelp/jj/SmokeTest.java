package ru.levelp.jj;

import org.junit.Test;
import ru.levelp.jj.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SmokeTest {
    @Test
    public void createUserTest() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        EntityManager entityManager = factory.createEntityManager();

        User user = new User("test", "pass");
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();

        entityManager.close();
        factory.close();
    }
}
