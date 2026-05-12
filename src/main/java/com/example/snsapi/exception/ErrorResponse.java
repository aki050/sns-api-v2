package com.example.snsapi.exception;

import java.time.LocalDateTime;
import java.util.List;


/**
 * ErrorResponseクラス
 * APIのエラーレスポンスを統一的に返すためのクラス。
 * バリデーションエラーや404エラーなど、全てのエラーで使用する。
 */

public class ErrorResponse{
  // ===========================
    // フィールド
    // ===========================
    
    /**
     * エラー発生日時
     * いつエラーが発生したかを記録する。
     */
    private LocalDateTime timestamp;

    /**
     * HTTPステータスコード
     * 例: 400, 404, 500
     */
    private int status;

    /**
     * エラーメッセージのリスト
     * 複数のエラーがある場合（例: usernameとemailの両方が不正）に対応。
     */
    private List<FieldError> errors;

    // ===========================
    // コンストラクタ
    // ===========================
    
    /**
     * 全フィールド指定コンストラクタ
     * 
     * @param timestamp エラー発生日時
     * @param status HTTPステータスコード
     * @param errors エラーメッセージのリスト
     */
    public ErrorResponse(LocalDateTime timestamp, int status, List<FieldError> errors) {
        this.timestamp = timestamp;
        this.status = status;
        this.errors = errors;
}
    // ===========================
    // Getter / Setter
    // ===========================
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public List<FieldError> getErrors() {
        return errors;
    }
    public void setErrors(List<FieldError> errors) {
        this.errors = errors;
    }

    // ===========================
    // 内部クラス：FieldError
    // ===========================

    /**
     * FieldErrorクラス
     * 個別のフィールドエラー情報を保持する。
     * 
     * 例:
     * {
     *   "field": "username",
     *   "message": "ユーザー名は必須です"
     * }
     */
    public static class FieldError {
        /**
         * エラーが発生したフィールド名
         * 例: "username", "email"
         */
        public String field;

        /**
         * エラーメッセージ
         * 例: "ユーザー名は必須です"
         */
        private String message;
        /**
         * コンストラクタ
         * 
         * @param field フィールド名
         * @param message エラーメッセージ
         */
        public FieldError(String field, String message) {
            this.field = field;
            this.message = message;
    }
        // Getter / Setter
        public String getField() {
            return field;
        }
        public void setField(String field) {
            this.field = field;
        }
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
    }
}