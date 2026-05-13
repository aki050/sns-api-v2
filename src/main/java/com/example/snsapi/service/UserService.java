package com.example.snsapi.service;

import com.example.snsapi.model.User;  // User エンティティ
import com.example.snsapi.repository.UserRepository;  // UserRepository をインポート
import org.springframework.beans.factory.annotation.Autowired;  // 依存性注入用
import org.springframework.stereotype.Service;  // @Service アノテーション

import java.util.List;  // List クラス
import java.util.Optional;  // Optional クラス

/**
 * UserService
 * ユーザーに関するビジネスロジックを担当するサービス層
 */
@Service  // このクラスが Service 層であることを示す
public class UserService {
    
    // UserRepository を注入（依存性注入 = DI）
    @Autowired  // Spring が自動でインスタンスを生成して注入してくれる
    private UserRepository userRepository;
    
    /**
     * 全ユーザーを取得
     * @return 全ユーザーのリスト
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();  // Repository の findAll() を呼ぶだけ
        // 生成される SQL: SELECT * FROM users
    }
    
    /**
     * IDでユーザーを取得
     * @param id ユーザーID
     * @return 該当ユーザー（存在しない場合は空のOptional）
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);  // Repository の findById() を呼ぶだけ
        // 生成される SQL: SELECT * FROM users WHERE id = ?
    }
    
    /**
     * ユーザーを作成
     * @param user 作成するユーザー
     * @return 作成されたユーザー（IDが自動採番される）
     */
    public User createUser(User user) {
        return userRepository.save(user);  // Repository の save() を呼ぶだけ
        // 生成される SQL: INSERT INTO users (username, email, created_at) VALUES (?, ?, ?)
    }
}