package ru.levelp.jj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.levelp.jj.logic.DashboardService;
import ru.levelp.jj.model.Transaction;
import tests.ru.levelp.jj.DashboardTestConfig;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
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
