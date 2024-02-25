package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.dao.UserDao;
import ru.otus.hw.dao.dto.User;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserDao userDao;

    private final IOService ioService;

    @Override
    public void runUserProfileCreator() {

        ioService.printLine("Welcome to program!");
        createUser();

    }

    private void createUser() {

        User user = new User();

        ioService.printWithoutNewLine("Enter your last name: ");
        user.setLastName(ioService.getStringUserInput());
        ioService.printWithoutNewLine("Enter your first name: ");
        user.setFirstName(ioService.getStringUserInput());

        userDao.saveUser(user);
    }
}
