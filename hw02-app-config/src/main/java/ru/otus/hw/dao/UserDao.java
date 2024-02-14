package ru.otus.hw.dao;

import ru.otus.hw.dao.dto.User;

public interface UserDao {
    User getUser();

    void saveUser(User user);

}
