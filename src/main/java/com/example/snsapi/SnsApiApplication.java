package com.example.snsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * SNS API アプリケーションのメインクラス
 * このクラスが、Spring Boot アプリケーションの開始地点（エントリーポイント）になります。
 */
@SpringBootApplication
public class SnsApiApplication {
    public static void main(String[] args) {
      // Spring Boot アプリケーションを起動します。
      // これにより、内蔵されている Web サーバー（Tomcat）が立ち上がります。
        SpringApplication.run(SnsApiApplication.class, args);
    }
  }
