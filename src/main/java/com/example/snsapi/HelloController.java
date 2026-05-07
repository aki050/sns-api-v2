package com.example.snsapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 動作確認用のコントローラークラス
 * 特定のURLにアクセスした際の挙動を定義します。
 */

@RestController
public class HelloController{
  /**
     * "http://localhost:8080/hello" にアクセスした際に実行されます。
     * @return 画面に表示する文字列
     */

  @GetMapping("/hello")
  public String sayHello(){
    // ブラウザに表示されるメッセージを返します。
    return "SNS API v2 へようこそ！サーバーは正常に動作しています。";
  }
}
