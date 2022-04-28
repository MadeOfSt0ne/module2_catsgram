package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.Exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.Exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.Exceptions.UserNotFoundException;
import ru.yandex.practicum.catsgram.models.User;

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
            throw new InvalidEmailException("Error. Bad email address");
        } else if (user.getEmail() != null) {
            throw new UserAlreadyExistException("Error. User already exists");
        }
        user.setUserId(getNextId());
        users.put(user.getUserId(), user);
        return user;
    }

    public User update(User user) throws InvalidEmailException {
        if (!users.containsKey(user.getUserId())) {
            throw new UserNotFoundException();
        }
        if (user.getEmail().isBlank() || user.getEmail() == null) {
            throw new InvalidEmailException("Error. Bad email address");
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
            throw new UserNotFoundException();
        }
        return users.get(id);
    }
}

