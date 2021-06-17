package tests.ru.levelp.jj;


import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
@ComponentScan(basePackages = {"ru.levelp.jj.logic", "tests.ru.levelp.jj"})
public class DashboardTestConfig {
    @MockBean
    public EntityManager manager;
}
