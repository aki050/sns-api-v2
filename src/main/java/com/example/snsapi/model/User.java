package com.example.snsapi.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * User クラス
 * SNSのユーザー情報を示すモデルクラス。
 * 将来的にPostと関連つけて「誰が投稿したか」を管理するために使用する。
 */

public class User{
    // ===========================
    // フィールド（プロパティ）
    // ===========================
    
    /**
     * ユーザーID
     * ユーザーを一意に識別するための番号。
     * Postクラスと同じく、Long型を使用（大きな数値に対応）。
     */
    private Long id;

    /**
     * ユーザー名
     * ユーザーの表示名（例: "aki", "tanaka"）。
     * SNS上で表示される名前として使用する。
     * 
     * @NotBlank：
     * null、空文字（""）、空白のみ（"   "）を全て拒否する。
     * 必ず何か文字が入力されている必要がある。
     */
    @NotBlank(message = "ユーザー名は必須です")
    private String username;

    /**
     * メールアドレス
     * ユーザーの連絡先情報。
     * 将来的にはログイン機能やパスワードリセットなどで使用する想定。
     * 
     * @NotBlank：
     * null、空文字、空白のみを拒否。
     * 
     * @Email：
     * メールアドレスの形式をチェック。
     * 例: "test@example.com" はOK、"invalid-email" はNG。
     */
    @NotBlank(message = "メールアドレスは必須です")
    @Email(message = "メールアドレスの形式が正しくありません")
    private String email;

    // ===========================
    // コンストラクタ
    // ===========================
    
    /**
     * デフォルトコンストラクタ
     * 引数なしでUserオブジェクトを作成する際に使用。
     * Spring Bootが内部でオブジェクトを生成する際に必要。
     */

    public User(){
    }
      /**
     * 全フィールド指定コンストラクタ
     * id, username, emailを指定してUserオブジェクトを作成する。
     * テストやサービス層で明示的にユーザーを作成する際に使用。
     * 
     * @param id ユーザーID
     * @param username ユーザー名
     * @param email メールアドレス
     */
    public User(Long id, String username, String email){
        this.id = id;               // 引数で受け取ったidをフィールドに代入
        this.username = username;   // 引数で受け取ったusernameをフィールドに代入
        this.email = email;         // 引数で受け取ったemailをフィールドに代入
    }
    
    // ===========================
    // Getter / Setter メソッド
    // ===========================
    
    /**
     * idのGetter
     * 外部からユーザーIDを取得する際に使用。
     * 
     * @return ユーザーID
     */
    public Long getId(){
        return id; // フィールドidの値を返す
    }

    /**
     * idのSetter
     * 外部からユーザーIDを設定する際に使用。
     * サービス層で新規ユーザーにIDを割り当てる際に使用する。
     * 
     * @param id 設定するユーザーID
     */
    public void setId(Long id){
        this.id = id; // 引数で受け取ったidをフィールドに代入
    }

    /**
     * usernameのGetter
     * 外部からユーザー名を取得する際に使用。
     * 
     * @return ユーザー名
     */
    public String getUsername(){
        return username; // フィールドusernameの値を返す
    }

    /**
     * usernameのSetter
     * 外部からユーザー名を設定する際に使用。
     * ユーザー登録時やプロフィール更新時に使用する。
     * 
     * @param username 設定するユーザー名
     */
    public void setUsername(String username){
        this.username = username; // 引数で受け取ったusernameをフィールドに代入
    }

    /**
     * emailのGetter
     * 外部からメールアドレスを取得する際に使用。
     * 
     * @return メールアドレス
     */
    public String getEmail(){
        return email; // フィールドemailの値を返す
    }

    /**
     * emailのSetter
     * 外部からメールアドレスを設定する際に使用。
     * ユーザー登録時やメールアドレス変更時に使用する。
     * 
     * @param email 設定するメールアドレス
     */
    public void setEmail(String email){
        this.email = email; // 引数で受け取ったemailをフィールドに代入
    }
}
