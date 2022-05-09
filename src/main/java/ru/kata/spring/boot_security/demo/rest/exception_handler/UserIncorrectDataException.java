package ru.kata.spring.boot_security.demo.rest.exception_handler;

public class UserIncorrectDataException extends RuntimeException{

    public UserIncorrectDataException(String message) {
        super(message);
    }
}
