package com.scistor.develop.data.entity;

import com.scistor.develop.data.ParentEntity;

import java.util.Date;

/**
 * 日志记录表
 */
public class OperationLog extends ParentEntity {

    public String operId;

    public String operModul;

    public String operType;

    public String operDesc;

    public String operRequParam;

    public String decrOperRequParam;

    public String operRespParam;

    public String operUserId;

    public String operUserName;

    public String operMethod;

    public String operUri;

    public String operIp;

    public Date operCreateTime;

    public String getDecrOperRequParam() {
        return decrOperRequParam;
    }

    public void setDecrOperRequParam(String decrOperRequParam) {
        this.decrOperRequParam = decrOperRequParam;
    }

    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId;
    }

    public String getOperModul() {
        return operModul;
    }

    public void setOperModul(String operModul) {
        this.operModul = operModul;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public String getOperDesc() {
        return operDesc;
    }

    public void setOperDesc(String operDesc) {
        this.operDesc = operDesc;
    }

    public String getOperRequParam() {
        return operRequParam;
    }

    public void setOperRequParam(String operRequParam) {
        this.operRequParam = operRequParam;
    }

    public String getOperRespParam() {
        return operRespParam;
    }

    public void setOperRespParam(String operRespParam) {
        this.operRespParam = operRespParam;
    }

    public String getOperUserId() {
        return operUserId;
    }

    public void setOperUserId(String operUserId) {
        this.operUserId = operUserId;
    }

    public String getOperUserName() {
        return operUserName;
    }

    public void setOperUserName(String operUserName) {
        this.operUserName = operUserName;
    }

    public String getOperMethod() {
        return operMethod;
    }

    public void setOperMethod(String operMethod) {
        this.operMethod = operMethod;
    }

    public String getOperUri() {
        return operUri;
    }

    public void setOperUri(String operUri) {
        this.operUri = operUri;
    }

    public String getOperIp() {
        return operIp;
    }

    public void setOperIp(String operIp) {
        this.operIp = operIp;
    }

    public Date getOperCreateTime() {
        return operCreateTime;
    }

    public void setOperCreateTime(Date operCreateTime) {
        this.operCreateTime = operCreateTime;
    }
}
