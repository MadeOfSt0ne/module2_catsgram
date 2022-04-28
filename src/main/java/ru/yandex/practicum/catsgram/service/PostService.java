package ru.yandex.practicum.catsgram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.Exceptions.PostNotFoundException;
import ru.yandex.practicum.catsgram.Exceptions.UserNotFoundException;
import ru.yandex.practicum.catsgram.models.Post;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final List<Post> posts = new ArrayList<>();
    private static Integer globalId;

    private final UserService userService;

    @Autowired
    public PostService(UserService userService) {
        this.userService = userService;
    }

    public List<Post> findAll() {
        return posts;
    }

    public Post create(Post post) {
        if (userService.findUserByEmail(post.getAuthor()) == null) {
            throw new UserNotFoundException();
        }
        post.setId(getNextId());
        posts.add(post);
        return post;
    }

    public static Integer getNextId() {
        return globalId++;
    }

    public Post findPostById(Integer id) {
        return posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(PostNotFoundException::new);
    }
}
