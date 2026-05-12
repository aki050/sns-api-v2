package com.example.snsapi.controller;

import com.example.snsapi.model.User;
import com.example.snsapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

/**
 * UserControllerクラス
 * ユーザーに関するHTTPリクエストを受け付けるコントローラー層。
 * クライアント（curl, ブラウザ, フロントエンド）からのリクエストを
 * UserServiceに渡し、結果をJSON形式で返却する。
 * 
 * @RestController：
 * このクラスがREST APIのコントローラーであることを示す。
 * メソッドの戻り値が自動的にJSON形式に変換される。
 * 
 * @RequestMapping("/api/users")：
 * このコントローラーのベースURLを指定。
 * 全てのエンドポイントは /api/users から始まる。
 */
@RestController
@RequestMapping("/api/users")
public class UserController{
    // ===========================
    // フィールド
    // ===========================
    
    /**
     * UserServiceのインスタンス
     * 
     * @Autowired：
     * SpringがUserServiceのインスタンスを自動的に注入（DI: Dependency Injection）。
     * new UserService() と書かなくても、Springが勝手に生成して渡してくれる。
     * 
     * private final：
     * 一度設定したら変更できない（不変）。
     * コンストラクタで設定されるので、nullになることがない。
     */
    @Autowired
    private final UserService userService;

    // ===========================
    // コンストラクタ
    // ===========================
    
    /**
     * コンストラクタインジェクション
     * 
     * Springが起動時にUserServiceのインスタンスを作成し、
     * このコンストラクタに渡してくれる。
     * フィールドインジェクション（@Autowiredをフィールドに付ける方法）より
     * テストしやすく、推奨される方法。
     * 
     * @param userService Springが注入するUserServiceインスタンス
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }
    // ===========================
    // エンドポイント
    // ===========================
    
    /**
     * 全ユーザー取得
     * 
     * @GetMapping：
     * HTTPのGETリクエストを受け付ける。
     * URLは /api/users（ベースURL + このメソッドのパス）
     * 
     * 処理の流れ：
     * 1. クライアントが GET /api/users にリクエスト
     * 2. このメソッドが呼ばれる
     * 3. userService.getAllUsers() でユーザーリストを取得
     * 4. Springが自動的にJSON形式に変換してレスポンス
     * 
     * @return 全ユーザーのリスト（JSON形式で返却される）
     */
    @GetMapping
    public List<User> getAllUsers(){
        // UserServiceのgetAllUsers()を呼び出して結果を返す
        return userService.getAllUsers();
    }
    // ID指定でユーザーを取得
    @GetMapping("/{id}")  // GET /api/users/{id} にマッピング
    public User getUserById(@PathVariable Long id){  // URLの{id}をLong型で受け取る
        // Serviceから Optional<User> を取得
        Optional<User> userOpt = userService.getUserById(id);
        // Optional が空（ユーザーが存在しない）の場合は例外を投げる
        return userOpt.orElseThrow(() -> 
            new RuntimeException("User not found: id=" + id) // 404エラー相当の例外
        );
    }
    /**
     * 新規ユーザー作成
     * 
     * @PostMapping：
     * HTTPのPOSTリクエストを受け付ける。
     * URLは /api/users
     * 
     * @RequestBody：
     * HTTPリクエストのボディ（JSON）を受け取り、Userオブジェクトに変換する。
     * 
     * @Valid：【新規追加】
     * Userオブジェクトのバリデーションを実行する。
     * User.javaに付けた@NotBlank、@Emailなどのアノテーションをチェック。
     * バリデーションエラーがあれば、自動的に400エラーを返す。
     * 
     * @param user クライアントから送られてきたUserオブジェクト（JSONから変換）
     * @return 作成されたUserオブジェクト（IDが設定済み、JSON形式で返却）
     */
    @PostMapping
    public User createUser(@Valid @RequestBody User user){
        // UserServiceのcreateUser()にユーザー情報を渡し、
        // IDが設定されたユーザーオブジェクトを返す
        return userService.createUser(user);
    }
}