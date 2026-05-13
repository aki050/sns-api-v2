package com.example.snsapi.model;

import jakarta.persistence.*;   // JPA のアノテーション（Entity, Table, Id など）をまとめてインポート
import java.time.LocalDateTime;     // 日時を扱うクラス

@Entity     // このクラスがDBのテーブルに対応するエンティティであることを示す
@Table(name = "posts")  // テーブル名を "posts" に指定（複数形が一般的）
public class Post {
  
  @Id // 主キー
  @GeneratedValue(strategy = GenerationType.IDENTITY)   // IDは自動生成される
  private Long id;          // 投稿ID
  
  @Column(nullable = false, length = 500) // NULL不可、最大500文字
  private String content;   // 投稿内容
  
  @Column(name = "user_id", nullable = false) // DBのカラム名を "user_id" に指定、NULL不可  
  private Long userId;      // 投稿者のユーザーID（誰が投稿したか）
  
  @Column(name = "created_at", nullable = false, updatable = false) // DBのカラム名を "created_at" に指定、NULL不可、更新不可
  private LocalDateTime createdAt; // 投稿日時（自動でセットされる）
  
  // JPA 用デフォルトコンストラクタ
  public Post() {
  }

  // コンストラクタ（全フィールド指定）
  public Post(Long id, String content, Long userId) {
    this.id = id;
    this.content = content;
    this.userId = userId;
  }
  
  // 投稿作成用コンストラクタ（IDは自動採番、createdAtは自動設定）
  public Post(String content, Long userId) {
    this.content = content;
    this.userId = userId;
    this.createdAt = LocalDateTime.now(); // 作成日時を自動設定
  }

  // Getter / Setter
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }
  
  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }
}