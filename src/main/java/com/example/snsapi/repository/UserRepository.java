package com.example.snsapi.repository;

import com.example.snsapi.model.User;  // User エンティティをインポート
import org.springframework.data.jpa.repository.JpaRepository;  // JpaRepository をインポート
import org.springframework.stereotype.Repository;  // Repository アノテーションをインポート

import java.util.Optional;  // Optional クラスをインポート
/**
 * UserRepository インターフェース
 * User エンティティのDB操作を担当するリポジトリ。
 * JpaRepository を継承することで、基本的なCRUD操作が自動的に提供される。
 */
@Repository  // Springにこのインターフェースがリポジトリ層であることを示す
public interface UserRepository extends JpaRepository<User, Long> {
  // JpaRepository<User, Long> の意味：
    // - User = 操作対象のエンティティ
    // - Long = 主キー（id）の型
    
    // 以下のメソッドが自動で使える（実装不要）：
    // - save(User user) : ユーザーを保存・更新
    // - findById(Long id) : IDでユーザーを検索
    // - findAll() : 全ユーザーを取得
    // - deleteById(Long id) : IDでユーザーを削除
    // - count() : ユーザー数をカウント

  // カスタムメソッド（メソッド名から自動で SQL を生成）
  Optional<User> findByUsername(String username);  // ユーザー名でユーザーを検索するメソッド
  // 生成される SQL: SELECT * FROM users WHERE username = ?
  boolean existsByUsername(String username);  // ユーザー名の重複をチェックするメソッド(usernameが既に存在するかどうかを返す)
  // 生成される SQL: SELECT COUNT(*) > 0 FROM users WHERE username = ?
}