package ru.yandex.practicum.catsgram.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.models.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.List;

@Slf4j
@RestController
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<Post> findAll() {
        log.debug("Текущее количество постов: {}", postService.findAll().size());
        return postService.findAll();
    }

    @GetMapping("/post/{postId}")
    public Post findPost(@PathVariable Integer postId) {
        return postService.findPostById(postId);
    }

    @PostMapping(value = "/post")
    public Post create(@RequestBody Post post) {
        log.debug("{}", post);
        return postService.create(post);
    }
}