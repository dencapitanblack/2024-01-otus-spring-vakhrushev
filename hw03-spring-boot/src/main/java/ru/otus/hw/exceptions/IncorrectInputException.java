package ru.otus.hw.exceptions;

public class IncorrectInputException extends AppException {
    public IncorrectInputException(String message, Throwable ex) {
        super(message, ex);
    }
}
