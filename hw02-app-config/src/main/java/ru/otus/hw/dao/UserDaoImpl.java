package ru.otus.hw.dao;

import org.springframework.stereotype.Repository;
import ru.otus.hw.dao.dto.User;

@Repository
public class UserDaoImpl implements UserDao {

    private User user;

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void saveUser(User user) {
        this.user = user;
    }
}
