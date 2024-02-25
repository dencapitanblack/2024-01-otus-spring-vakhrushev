package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.hw.config.AppProperties;
import ru.otus.hw.dao.UserDao;
import ru.otus.hw.dao.dto.User;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserDao userDao;

    private final IOService ioService;

    private final AppProperties appProperties;

    private final MessageSource messageSource;

    @Override
    public void runUserProfileCreator() {

        ioService.printLine(messageSource.getMessage("user.welcome", null, appProperties.getLocale()));
        createUser();

    }

    private void createUser() {

        User user = new User();

        ioService.printWithoutNewLine(messageSource.getMessage("user.lastname", null, appProperties.getLocale()));
        user.setLastName(ioService.getStringUserInput());
        ioService.printWithoutNewLine(messageSource.getMessage("user.firstname", null, appProperties.getLocale()));
        user.setFirstName(ioService.getStringUserInput());

        userDao.saveUser(user);
    }
}
