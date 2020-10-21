package com.scistor.develop.data.entity;

import com.scistor.develop.data.ParentEntity;

import java.util.Date;

public class Regulatory extends ParentEntity {

    public Long id;
    public Date dataTime;
    public String code;
    public String describe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataTime() {
        return dataTime;
    }

    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
