package ru.yandex.practicum.catsgram.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.Exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.Exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.models.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final HashMap<String, User> users = new HashMap<>();

    @GetMapping()
    public Map<String, User> getUsers() {
        log.trace("Количество пользователей: {}", users.size());
        return users;
    }

    @PostMapping()
    public void addUser(@RequestBody User user) throws InvalidEmailException, UserAlreadyExistException {
        if (user.getEmail().isBlank() || user.getEmail() == null) {
            throw new InvalidEmailException("Error. Bad email address");
        } else if (users.containsKey(user.getEmail())) {
            throw new UserAlreadyExistException("Error. User already exists");
        } else {
            log.trace("{}", user);
            users.put(user.getEmail(), user);
        }
    }

    @PutMapping
    public void updateUser(@RequestBody User user) throws InvalidEmailException {
        if (user.getEmail().isBlank() || user.getEmail() == null) {
            throw new InvalidEmailException("Error. Bad email address");
        } else {
            users.put(user.getEmail(), user);
        }
    }
}
