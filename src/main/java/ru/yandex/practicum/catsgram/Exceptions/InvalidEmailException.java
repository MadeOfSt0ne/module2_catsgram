package ru.yandex.practicum.catsgram.Exceptions;

public class InvalidEmailException extends Exception {
    public InvalidEmailException(String s) {

    }

    public String toString() {
        return "Error. Bad email adress";
    }
}
