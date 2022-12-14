package com.example.common.api;

/**
 * API Code
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    NOT_FOUND(404, "無資料"),
    UNAUTHORIZED(401, "暂未登錄或token已经過期"),
    FORBIDDEN(403, "没有相關權限");
    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
