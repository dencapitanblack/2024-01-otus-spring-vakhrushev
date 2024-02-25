package ru.otus.hw.exceptions;

public class IncorrectInputException extends RuntimeException {
    public IncorrectInputException(String message, Throwable ex) {
        super(message, ex);
    }
}
