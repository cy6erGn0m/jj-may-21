package ru.levelp.jj.web;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ru.levelp.jj.TestConfig;
import ru.levelp.jj.dao.TransactionsDAO;
import ru.levelp.jj.model.Transaction;
import ru.levelp.jj.model.User;
import tests.ru.levelp.jj.DashboardTestConfig;

import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = DashboardTestConfig.class)
@ComponentScan(basePackages = "ru.levelp.jj.web")
class MainPageControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private TransactionsDAO transactions;

    @Test
    public void testIndex() throws Exception {
        List<Transaction> expectedTransactions = List.of(
                new Transaction(new Date(), 1, new User(), new User()),
                new Transaction(new Date(), 1, new User(), new User()),
                new Transaction(new Date(), 1, new User(), new User()),
                new Transaction(new Date(), 1, new User(), new User())
        );
        when(transactions.findLast(3)).thenReturn(expectedTransactions.subList(0, 3));
        when(transactions.findLast(10)).thenReturn(expectedTransactions);
        when(transactions.findLast(anyInt())).thenReturn(expectedTransactions);

        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(request().sessionAttribute("userSession", new UserSession()))
                .andExpect(model().attribute("lastTransactions", expectedTransactions));

        Mockito.verify(transactions, times(1)).findLast(10);
    }

    @Test
    public void testIndexWithCount() throws Exception {
        List<Transaction> expectedTransactions = List.of(
                new Transaction(new Date(), 1, new User(), new User()),
                new Transaction(new Date(), 1, new User(), new User()),
                new Transaction(new Date(), 1, new User(), new User())
        );
        when(transactions.findLast(3)).thenReturn(expectedTransactions);

        mvc.perform(get("/?count=3"))
                .andExpect(status().isOk())
                .andExpect(request().sessionAttribute("userSession", new UserSession()))
                .andExpect(model().attribute("lastTransactions", expectedTransactions));

        Mockito.verify(transactions, times(1)).findLast(3);
    }

    @Test
    public void testIndexLoggedInUser() throws Exception {
        UserSession session = new UserSession();
        session.setUserId(1);
        session.setLogin("login1");

        mvc.perform(
                get("/").sessionAttr("userSession", session)
        )
                .andExpect(status().isOk())
                .andExpect(request().sessionAttribute("userSession", session));
    }
}
