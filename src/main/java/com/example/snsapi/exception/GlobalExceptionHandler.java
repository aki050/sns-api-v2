package com.example.snsapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * GlobalExceptionHandlerクラス
 * アプリケーション全体の例外を一元管理するクラス。
 * 
 * @RestControllerAdvice：
 * 全てのコントローラーで発生した例外をキャッチして処理する。
 * Controller単位ではなく、アプリケーション全体に適用される。
 */

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * バリデーションエラーのハンドリング
     * 
     * @ExceptionHandler：
     * どの例外をキャッチするか指定する。
     * MethodArgumentNotValidException = @Validによるバリデーションエラー
     * 
     * @ResponseStatus：
     * 返却するHTTPステータスコードを指定する。
     * HttpStatus.BAD_REQUEST = 400エラー
     * 
     * @param ex キャッチした例外オブジェクト
     * @return ErrorResponse（統一されたエラーレスポンス）
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        // 1. エラーメッセージのリストを作成（空のリスト）
        List<ErrorResponse.FieldError> fieldErrors = new ArrayList<>();

        // 2. 例外オブジェクトから全てのフィールドエラーを取得
        //    getBindingResult() = バリデーション結果を取得
        //    getAllErrors() = 全てのエラーを取得
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            // 3. 各エラーをErrorResponse.FieldErrorに変換
            //    error.getField() = エラーが発生したフィールド名（例: "username"）
            //    error.getDefaultMessage() = User.javaで設定したメッセージ（例: "ユーザー名は必須です"）
            fieldErrors.add(new ErrorResponse.FieldError(
                error.getField(),
                error.getDefaultMessage()
            ));
        }
        // 4. ErrorResponseオブジェクトを作成して返す
        //    LocalDateTime.now() = 現在時刻
        //    400 = HTTPステータスコード
        //    fieldErrors = エラーメッセージのリスト
        return new ErrorResponse(
            LocalDateTime.now(),
            400,
            fieldErrors
        );
    }
}