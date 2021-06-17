package ru.levelp.jj.web;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.levelp.jj.dao.UsersDAO;
import ru.levelp.jj.model.User;
import tests.ru.levelp.jj.DashboardTestConfig;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = DashboardTestConfig.class)
@ComponentScan(basePackages = "ru.levelp.jj.web")
@AutoConfigureMockMvc
public class LoginControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UsersDAO users;

    @Test
    public void testLoginForm() throws Exception {
        User user = new User("user", "123");
        user.setId(1);

        when(users.findByLoginAndPassword("user", "123"))
                .thenReturn(user);

        mvc.perform(post("/login")
                .param("login", "user")
                .param("password", "123")
        ).andExpect(status().is3xxRedirection())
                .andExpect(request().sessionAttribute(
                        "userSession",
                        new UserSession(1, "user")
                ));
    }

    @Test
    public void testLoginFormInvalidUser() throws Exception {
        mvc.perform(post("/login")
                .param("login", "user")
                .param("password", "1234")
        ).andExpect(status().is3xxRedirection())
                .andExpect(request().sessionAttribute(
                        "userSession",
                        new UserSession()
                ));
    }
}
