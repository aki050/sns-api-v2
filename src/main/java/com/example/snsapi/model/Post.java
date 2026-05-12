package com.example.snsapi.model;

public class Post{
  private Long id;          // 投稿ID
  private String content;   // 投稿内容
  private Long userId;      // 投稿者のユーザーID（誰が投稿したか）
  
  // コンストラクタ（全フィールド指定）
  public Post(Long id, String content, Long userId){
    this.id = id;
    this.content = content;
    this.userId = userId;
  }

  // Getter / Setter
  public Long getId(){
    return id;
  }

  public void setId(Long id){
    this.id = id;
  }

  public String getContent(){
    return content;
  }

  public void setContent(String content){
    this.content = content;
  }

  public Long getUserId(){
    return userId;
  }

  public void setUserId(Long userId){
    this.userId = userId;
  }
}