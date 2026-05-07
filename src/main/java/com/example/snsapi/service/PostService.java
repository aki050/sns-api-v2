package com.example.snsapi.service;

import com.example.snsapi.model.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 投稿に関するビジネスロジックを処理するサービス
 */

@Service
public class PostService {
  // 投稿データを一時的に保存するためのリスト（メモリ上の簡易データベース）
  private final List<Post> posts = new ArrayList<>();
  private Long nextId = 1L; //次に発行するID

  /**
     * すべての投稿を取得します。
     * @return 投稿リスト
     */
  public List<Post> getAllPosts() {
    return posts;
  }

  /**
     * 新しい投稿を保存します。
     * @param post 投稿データ
     * @return 保存された投稿
     */

  public Post createPost(Post post) {
    post.setId(nextId++); // IDを自動的に割り当てる
    posts.add(post); // IDを自動採番
    return post; // リストに追加
  }
}
