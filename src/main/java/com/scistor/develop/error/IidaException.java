package com.scistor.develop.error;

/**
 * 自定义异常类
 * 注：spring 对于 RuntimeException 异常才会进行事务回滚。
 */

public class IidaException extends RuntimeException {

    private String code = null;         // 错误编码
    private String msg = null;          // 出错消息

    public IidaException(String code, String msg) throws IidaException {
        super();
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
