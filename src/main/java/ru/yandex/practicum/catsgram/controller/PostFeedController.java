package ru.yandex.practicum.catsgram.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PostFeedController {

    private final PostService postService;

    public PostFeedController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/feed/friends")
    public List<Post> getFriendsFeed(@RequestBody String params) {
        ObjectMapper objectMapper = new ObjectMapper();
        FriendsParams friendsParams;
        try {
            String paramsFromString = objectMapper.readValue(params, String.class);
            friendsParams = objectMapper.readValue(paramsFromString, FriendsParams.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Невалидный формат json", e);
        }
        if (friendsParams != null) {
            List<Post> result = new ArrayList<>();
            for (String friend: friendsParams.friends) {
                result.addAll(postService.findAllByUserEmail(friend, friendsParams.size, friendsParams.sort));
            }
            return result;
        } else {
            throw new RuntimeException("Неверно заполнены параметры");
        }
    }

    @Data
    static class FriendsParams {
        private String sort;
        private Integer size;
        private List<String> friends;
    }
}
