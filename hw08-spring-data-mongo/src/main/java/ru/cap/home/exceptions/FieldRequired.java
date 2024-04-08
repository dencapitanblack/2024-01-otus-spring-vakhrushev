package ru.cap.home.exceptions;

public class FieldRequired extends RuntimeException {
    public FieldRequired(String message) {
        super(message, new RuntimeException());
    }
}
