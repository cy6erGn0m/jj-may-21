package tests.ru.levelp.jj;

import org.springframework.stereotype.Repository;
import ru.levelp.jj.dao.TransactionsDAOImpl;
import ru.levelp.jj.model.Transaction;
import ru.levelp.jj.model.User;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Repository
public class TestTransactionDAO extends TransactionsDAOImpl {

    @Override
    public List<Transaction> findByUser(User user) {
        return Collections.singletonList(
                new Transaction(new Date(), 100.0, user,
                        new User("test-user", "pass")
                )
        );
    }
}
