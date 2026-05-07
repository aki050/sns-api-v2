package com.example.snsapi.controller;

import com.example.snsapi.model.Post;
import com.example.snsapi.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 投稿に関するリクエストを受け付ける窓口（コントローラー）
 */
@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    // PostServiceをこのクラスで使えるようにセットします（コンストラクタ注入）
    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * 投稿一覧を取得する (GET http://localhost:8080/posts)
     */
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    /**
     * 新しく投稿を作る (POST http://localhost:8080/posts)
     */
    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }
}
