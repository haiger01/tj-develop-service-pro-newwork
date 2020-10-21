package com.scistor.develop.data.entity;

import com.scistor.develop.data.ParentEntity;

import java.util.Date;

public class ExceptionLog extends ParentEntity {

    public String excId;

    public String excRequParam;

    public String decrExcRequParam;

    public String excName;

    public String excMessage;

    public String operUserId;

    public String operUserName;

    public String operMethod;

    public String operUri;

    public String operIp;

    public Date operCreateTime;

    public String getDecrExcRequParam() {
        return decrExcRequParam;
    }

    public void setDecrExcRequParam(String decrExcRequParam) {
        this.decrExcRequParam = decrExcRequParam;
    }

    public String getExcId() {
        return excId;
    }

    public void setExcId(String excId) {
        this.excId = excId;
    }

    public String getExcRequParam() {
        return excRequParam;
    }

    public void setExcRequParam(String excRequParam) {
        this.excRequParam = excRequParam;
    }

    public String getExcName() {
        return excName;
    }

    public void setExcName(String excName) {
        this.excName = excName;
    }

    public String getExcMessage() {
        return excMessage;
    }

    public void setExcMessage(String excMessage) {
        this.excMessage = excMessage;
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
