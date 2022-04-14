package ru.yandex.practicum.catsgram.controllers;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.Exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.Exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.models.User;

import java.util.HashMap;
import java.util.Map;

@RestController("/users")
public class UserController {

    private final HashMap<String, User> users = new HashMap<>();

    @GetMapping()
    public Map<String, User> getUsers() {
        return users;
    }

    @PostMapping()
    public void addUser(@RequestBody String email, User user) throws InvalidEmailException, UserAlreadyExistException {
        if (email.isBlank()) {
            throw new InvalidEmailException("Error. Bad email adress");
        } else if (users.containsKey(email)) {
            throw new UserAlreadyExistException("Error. User already exists");
        } else {
            users.put(email, user);
        }
    }

    @PutMapping
    public void updateUser(@RequestBody String email, User user) throws InvalidEmailException {
        if (email.isBlank()) {
            throw new InvalidEmailException("Error. Bad email adress");
        } else {
            users.put(email, user);
        }
    }
}
