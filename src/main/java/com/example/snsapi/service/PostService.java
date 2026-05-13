package com.example.snsapi.service;

import com.example.snsapi.model.Post; // Post エンティティ
import com.example.snsapi.repository.PostRepository;  // PostRepository をインポート
import org.springframework.beans.factory.annotation.Autowired;  // 依存性注入用
import org.springframework.stereotype.Service;  // @Service アノテーション

import java.util.ArrayList;
import java.util.List;  // List クラス

/**
 * PostService
 * 投稿に関するビジネスロジックを担当するサービス層
 */

@Service  // このクラスが Service 層であることを示す
public class PostService {
  // PostRepository を注入（依存性注入 = DI）
  @Autowired  // Spring が自動でインスタンスを生成して注入してくれる
  private PostRepository postRepository;

  /**
     * 全投稿を取得
     * @return 全投稿のリスト
     */
  public List<Post> getAllPosts() {
    return postRepository.findAll();  // Repository の findAll() を呼ぶだけ
    // 生成される SQL: SELECT * FROM posts 
  }

  /**
     * 投稿を作成
     * @param post 作成する投稿
     * @return 作成された投稿（IDが自動採番される）
     */

  public Post createPost(Post post) {
    return postRepository.save(post);  // Repository の save() を呼ぶだけ
    // 生成される SQL: INSERT INTO posts (user_id, content, created_at) VALUES (?, ?, ?)
  }
/**
     * 全投稿を削除
     * @param id 削除する投稿のID
     * @return 削除に成功した場合は true
     */

  public boolean deletePost(Long id) {
    if (postRepository.existsById(id)) {  // IDが存在するか確認
      postRepository.deleteById(id);  // DBから削除
      // 生成される SQL: DELETE FROM posts WHERE id = ?
      return true;  // 削除成功
    }
    return false;  // 削除失敗（IDが存在しない）
  }

  /**
     * 特定ユーザーの投稿一覧を取得
     * @param userId ユーザーID
     * @return 該当ユーザーの投稿リスト
     */
    public List<Post> getPostsByUserId(Long userId) {
        return postRepository.findByUserId(userId);  // Repository の findByUserId() を呼ぶだけ(カスタムメソッド)
        // 生成される SQL: SELECT * FROM posts WHERE user_id = ?
    }
}
