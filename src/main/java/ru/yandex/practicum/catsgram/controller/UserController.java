package ru.yandex.practicum.catsgram.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.service.UserService;

import java.util.*;

@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        log.debug("Количество пользователей: {}", userService.findAll().size());
        return userService.findAll();
    }

    @GetMapping("/users/{userId}")
    public User findUser(@PathVariable Integer userId) {
        return userService.findUserById(userId);
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) throws UserAlreadyExistException, InvalidEmailException {
        log.debug("{}", user);
        return userService.create(user);
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) throws InvalidEmailException {
        log.debug("Данные пользователя {} обновлены.", user.getNickname());
        return userService.update(user);
    }
}

