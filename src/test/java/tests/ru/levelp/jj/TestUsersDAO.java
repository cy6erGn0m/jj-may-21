package tests.ru.levelp.jj;

import org.springframework.stereotype.Repository;
import ru.levelp.jj.dao.UsersDAO;
import ru.levelp.jj.model.User;

@Repository
public class TestUsersDAO extends UsersDAO {
    @Override
    public User findById(int id) {
        if (id == 1) {
            User user = new User("user", "pass");
            user.setId(1);
            return user;
        }

        return null;
    }
}
