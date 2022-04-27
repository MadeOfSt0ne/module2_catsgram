package ru.yandex.practicum.catsgram.Exceptions;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String s) {

    }

    public String toString() {
        return "Error. Bad email address";
    }
}
