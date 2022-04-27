package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.Exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.Exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    private final HashMap<String, User> users = new HashMap<>();

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public User create(User user) throws InvalidEmailException, UserAlreadyExistException {
        if (user.getEmail().isBlank() || user.getEmail() == null) {
            throw new InvalidEmailException("Error. Bad email address");
        } else if (users.containsKey(user.getEmail())) {
            throw new UserAlreadyExistException("Error. User already exists");
        }
        users.put(user.getEmail(), user);
        return user;
    }

    public User update(User user) throws InvalidEmailException {
        if (user.getEmail().isBlank() || user.getEmail() == null) {
            throw new InvalidEmailException("Error. Bad email address");
        }
        users.put(user.getEmail(), user);
        return user;
    }

    public User findUserByEmail(String email) {
        return users.getOrDefault(email, null);
    }
}

