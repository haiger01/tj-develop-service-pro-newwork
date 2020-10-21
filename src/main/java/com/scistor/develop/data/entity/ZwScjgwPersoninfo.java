package com.scistor.develop.data.entity;

/**
 * 市场监管委企业人员信息表
 * 表：scjgw_personinfo
 */
public class ZwScjgwPersoninfo {

    public String entId;    //企业ID

    public String name;     //姓名

    public String position;     //职务

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}