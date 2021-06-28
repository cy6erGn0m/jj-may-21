package ru.levelp.jj;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.levelp.jj.dao.TransactionsDAO;
import ru.levelp.jj.logic.DashboardService;
import ru.levelp.jj.model.Transaction;
import ru.levelp.jj.model.User;
import tests.ru.levelp.jj.DashboardTestConfig;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ContextConfiguration(classes = DashboardTestConfig.class)
public class DashboardServiceTest {
    @Autowired
    private DashboardService dashboard;

    @MockBean
    private TransactionsDAO transactions;

    @Test
    public void findByUserId() {
        User user = new User("test-sender", "pass");
        user.setId(1);

        Mockito.when(transactions.findByUser(any())).thenReturn(
                Collections.singletonList(
                        new Transaction(new Date(), 100.0, user,
                                new User("test-user", "pass")
                        )
                )
        );

        List<Transaction> found = dashboard.findByUserId(1);
        assertEquals(1, found.size());
        assertEquals(1, found.get(0).getSender().getId());
    }
}
