package ru.yandex.practicum.catsgram.Exceptions;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException(String s) {

    }

    public String toString() {
        return "Error. User already exists";
    }
}
