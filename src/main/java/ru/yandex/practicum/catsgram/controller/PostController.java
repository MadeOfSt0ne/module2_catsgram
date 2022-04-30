package ru.yandex.practicum.catsgram.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.Post;
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
    public List<Post> findAll(
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
            @RequestParam(value = "sort", defaultValue = "asc", required = false) String sort) {
        if (!(sort.equals("asc") || sort.equals("desc"))) {
            throw new IllegalArgumentException();
        }
        if (size <= 0 || page < 0) {
            throw new IllegalArgumentException();
        }
        Integer from = page * size;

        return postService.findAll(size, from, sort);
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