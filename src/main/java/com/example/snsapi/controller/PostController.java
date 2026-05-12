package com.example.snsapi.controller;

import com.example.snsapi.model.Post;
import com.example.snsapi.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 投稿に関するリクエストを受け付ける窓口（コントローラー）
 */
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    // PostServiceをこのクラスで使えるようにセットします（コンストラクタ注入）
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 特定ユーザーの投稿一覧を取得 (GET http://localhost:8080/posts)
    @GetMapping("/user/{userId}")   // GET /api/posts/user/{userId} にマッピング
    public List<Post> getPostsByUserId(@PathVariable Long userId) {    // URLの{userId}を引数に受け取る// URLの{userId}をLong型で受け取る
        return postService.getPostsByUserId(userId);    // Serviceから該当ユーザーの投稿一覧を取得して返す
    }

     // 投稿を作成 (POST http://localhost:8080/posts)
    @PostMapping     // POST /api/posts にマッピング
    public Post createPost(@RequestBody Post post) {    // リクエストボディからPostオブジェクトを受け取る
        return postService.createPost(post);    // Serviceで作成してそのまま返す
    }

    /**
     * 投稿を削除する (DELETE /posts/{id})
     */
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
