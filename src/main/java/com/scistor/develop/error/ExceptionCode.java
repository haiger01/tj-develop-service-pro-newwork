package com.scistor.develop.error;

/**
 * @author 岳浩
 * 全局异常处理类
 */

public enum ExceptionCode {
    codeF2("-2", "loginError"),
    codeF1("-1", "noLogin"),
    code200("200", "系统正常"),
    code201("201", "正常,进入聚焦模式"),
    code202("202", "参数已过期"),
    code203("203", "请先生成文件"),
    code300("300", "数据格式错误,需客户端清除现有数据"),
    code301("301", "参数错误"),
    code302("302", "无权限，当前操作已记录"),
    code303("303", "未实名"),
    code304("304", "已存在一个未完结的需求"),
    code401("401", "访问频繁"),
    code5001("5001", "系统异常"),
    code5002("5002", "sqlError"),
    code5003("5003", "未获取到privateKey");
    private String code = "200";
    private String msg = "系统正常";

    private ExceptionCode() {
    }

    private ExceptionCode(String code, String msg) {
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
