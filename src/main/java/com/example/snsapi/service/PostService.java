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

  // 投稿を作成（IDを自動採番して保存）
  public Post createPost(Post post) {
    post.setId(nextId++);   // IDを自動採番（1, 2, 3...）
    posts.add(post);        // リストに追加
    return post;            // 作成した投稿を返す
  }
/**
     * 指定されたIDの投稿を削除します。
     * @param id 削除したい投稿のID
     * @return 削除に成功した場合は true
     */

  public boolean deletePost(Long id) {
    // リストの中から、IDが一致するものを探して削除する
    return posts.removeIf(post -> post.getId().equals(id));
  }

  // 特定ユーザーの投稿一覧を取得
  public List<Post> getPostsByUserId(Long userId) {
    List<Post> userPosts = new ArrayList<>();   // 結果を格納するリスト
    for (Post post : posts) {   // 全投稿をループ
      if (post.getUserId() != null && post.getUserId().equals(userId)) {    // userIdが一致するか確認
        userPosts.add(post);    // 一致したら結果リストに追加
      }
    }
    return userPosts;   // ユーザーの投稿一覧を返す
  }
}
