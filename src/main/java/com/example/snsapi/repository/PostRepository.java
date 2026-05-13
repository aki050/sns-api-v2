package com.example.snsapi.repository;

import com.example.snsapi.model.Post;  // Post エンティティをインポート
import org.springframework.data.jpa.repository.JpaRepository;  // JpaRepository をインポート
import org.springframework.stereotype.Repository;  // Repository アノテーションをインポート

import java.util.List;  // List クラスをインポート

/**
 * PostRepository インターフェース
 * Post エンティティのDB操作を担当するリポジトリ。
 * JpaRepository を継承することで、基本的なCRUD操作が自動的に提供される。
 */
@Repository  // Springにこのインターフェースがリポジトリ層であることを示す
public interface PostRepository extends JpaRepository<Post, Long> {
  // JpaRepository<Post, Long> の意味：
    // - Post = 操作対象のエンティティ
    // - Long = 主キー（id）の型
    
    // 以下のメソッドが自動で使える（実装不要）：
    // - save(Post post) : 投稿を保存・更新
    // - findById(Long id) : IDで投稿を検索
    // - findAll() : 全投稿を取得
    // - deleteById(Long id) : IDで投稿を削除
    // - count() : 投稿数をカウント

  // カスタムメソッド（メソッド名から自動で SQL を生成）
  List<Post> findByUserId(Long userId);  // ユーザーIDで投稿を検索するメソッド
  // 生成される SQL: SELECT * FROM posts WHERE user_id = ? 
}
