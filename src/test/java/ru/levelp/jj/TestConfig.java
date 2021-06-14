package ru.levelp.jj;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import ru.levelp.jj.configs.ProdJpaConfig;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan(
        basePackages = "ru.levelp.jj",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = {ProdJpaConfig.class, AppMain.class}
        )
)
public class TestConfig {
    @Bean
    public EntityManagerFactory factory() {
        return Persistence.createEntityManagerFactory("TestPersistenceUnit");
    }
}
