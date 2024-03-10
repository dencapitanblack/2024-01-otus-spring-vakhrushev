package ru.otus.hw.security;

import org.springframework.stereotype.Component;

@Component
public class InMemoryLoginContext implements LoginContext {

    private boolean isUserLoggedIn;

    @Override
    public void login() {
        isUserLoggedIn = true;
    }

    @Override
    public boolean isUserLoggedIn() {
        return isUserLoggedIn;
    }
}
