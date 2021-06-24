package ru.levelp.jj.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
public class AppConfig {
//    @Bean
//    public EntityManager entityManager(EntityManagerFactory factory) {
//        return factory.createEntityManager();
//    }
}
