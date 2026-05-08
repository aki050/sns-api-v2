package com.example.snsapi.service;

import com.example.snsapi.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * UserServiceクラス
 * ユーザーに関するビジネスロジックを担当するサービス層。
 * ユーザーの作成、取得、管理を行う。
 * 
 * @Serviceアノテーション：
 * このクラスがSpring BootのServiceレイヤーであることを示す。
 * Springコンテナに登録され、他のクラスから@Autowiredで注入できるようになる。
 */

@Service
public class UserService {

    // ===========================
    // フィールド
    // ===========================
    
    /**
     * ユーザーリスト（メモリ内保存）
     * ArrayListを使って、作成されたユーザーをメモリ上で管理する。
     * 将来的にはデータベース（H2, PostgreSQL等）に切り替える予定。
     * 
     * new ArrayList<>()：空のリストを初期化
     */
    private List<User> users = new ArrayList<>();
    /**
     * 次に割り当てるユーザーID
     * ユーザーが作成されるたびに1ずつ増えていく。
     * 初期値は1L（Long型の1）。
     */
    private Long nextId = 1L;

    // ===========================
    // メソッド
    // ===========================
    
    /**
     * 全ユーザーを取得する
     * 
     * 現在メモリ上に保存されている全てのユーザーのリストを返す。
     * Controller層から呼ばれて、API経由でクライアントに返却される。
     * 
     * @return 全ユーザーのリスト
     */
    public List<User> getAllUsers(){
        return users;
    }
    /**
     * 指定されたIDのユーザーを取得する
     * 
     * リストの中から、IDが一致するユーザーを探して返す。
     * 見つからない場合はnullを返す。
     * 
     * @param id 取得したいユーザーのID
     * @return 該当するUserオブジェクト、見つからない場合はnull
     */
    public User getUserById(Long id){
      // users リストをループで1つずつ確認
        for (User user : users){
            // user.getId()で取得したIDと、引数のidが一致するか比較
            // Long型の比較は「==」ではなく「.equals()」を使う
            if (user.getId().equals(id)){
              // 一致したらそのユーザーを返して処理終了
                return user;
            }
        }
        // ループを全て回っても見つからなかった場合はnullを返す
        return null;
    }
    /**
     * 新しいユーザーを作成する
     * 
     * クライアントから送られてきたユーザー情報（username, email）を受け取り、
     * サーバー側で自動的にIDを割り当ててリストに追加する。
     * 
     * @param user クライアントから送られてきたUserオブジェクト（IDはnull）
     * @return IDが設定された完全なUserオブジェクト
     */

    public User createUser(User user){
        // 1. 現在のnextIdをこのユーザーのIDとして設定
        user.setId(nextId);
        // 2. nextIdを1増やして、次のユーザー用に準備
        nextId++;
        // 3. usersリストに追加（メモリ上に保存）
        users.add(user);
        // 4. IDが設定されたユーザーオブジェクトを返す
        return user;
    }
}