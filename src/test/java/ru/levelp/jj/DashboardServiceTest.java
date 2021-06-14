package ru.levelp.jj;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.levelp.jj.logic.DashboardService;
import ru.levelp.jj.model.Transaction;
import tests.ru.levelp.jj.DashboardTestConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes = DashboardTestConfig.class)
public class DashboardServiceTest {
    @Autowired
    private DashboardService dashboard;

    @Test
    public void findByUserId() {
        List<Transaction> found = dashboard.findByUserId(1);
        assertEquals(1, found.size());
        assertEquals(1, found.get(0).getSender().getId());
    }
}
