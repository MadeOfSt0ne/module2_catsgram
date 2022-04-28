package ru.yandex.practicum.catsgram.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.Exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.Exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.models.User;
import ru.yandex.practicum.catsgram.service.UserService;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getUsers() {
        log.debug("Количество пользователей: {}", userService.findAll().size());
        return userService.findAll();
    }

    @GetMapping(@PathVariable)

    @PostMapping()
    public User addUser(@RequestBody User user) throws UserAlreadyExistException, InvalidEmailException {
        log.debug("{}", user);
        return userService.create(user);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) throws InvalidEmailException {
        log.debug("Данные пользователя {} обновлены.", user.getNickname());
        return userService.update(user);
    }
}

