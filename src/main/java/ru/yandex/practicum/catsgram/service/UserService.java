package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.exception.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    private static Integer globalId;
    private final HashMap<Integer, User> users = new HashMap<>();

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public static Integer getNextId() {
        return globalId++;
    }

    public User create(User user) throws InvalidEmailException, UserAlreadyExistException {
        if (user.getEmail().isBlank() || user.getEmail() == null) {
            throw new InvalidEmailException("Почта не может быть пустой!");
        } else if (user.getEmail() != null) {
            throw new UserAlreadyExistException("Пользователь с почтой " + user.getEmail() + " уже существует.");
        }
        user.setUserId(getNextId());
        users.put(user.getUserId(), user);
        return user;
    }

    public User update(User user) throws InvalidEmailException {
        if (!users.containsKey(user.getUserId())) {
            throw new UserNotFoundException(String.format("Пользователь %d не найден!", user.getUserId()));
        }
        if (user.getEmail().isBlank() || user.getEmail() == null) {
            throw new InvalidEmailException("Почта не может быть пустой!");
        }
        users.put(user.getUserId(), user);
        return user;
    }

    public User findUserByEmail(String email) {
        return users.values().stream()
                .filter(p -> p.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public User findUserById(Integer id) {
        if (!users.containsKey(id)) {
            throw new UserNotFoundException("Пользователь с id " + id + " не найден!");
        }
        return users.get(id);
    }
}

