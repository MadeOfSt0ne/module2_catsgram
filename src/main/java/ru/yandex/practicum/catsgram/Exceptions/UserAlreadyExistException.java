package ru.yandex.practicum.catsgram.Exceptions;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String s) {

    }

    public String toString() {
        return "Error. User already exists";
    }
}
