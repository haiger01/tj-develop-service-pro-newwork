package com.scistor.develop.data.entity;

import com.scistor.develop.data.ParentEntity;

import java.util.Date;

public class AuthorizationKeys extends ParentEntity {
    public Long id;                     //表主键

    public String companyId;            //企业ID

    public String companyName;          //企业名称

    public String keyCode;              //授权码

    public String dataLabelTypes;       //一件对接的数据标签类型

    public Date createrTime;           //数据创建时间

    public Date updateTime;            //数据修改时间

    public Integer deleteFlag;          //'1:删除，0：未删除',

    public Long loanAppliId;            //受理表ID

    public String companyType;          //企业类型

    public String usercode;          //用户编号

    public String createrUser;          //创建人

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public Date getCreaterTime() {
        return createrTime;
    }

    public void setCreaterTime(Date createrTime) {
        this.createrTime = createrTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getLoanAppliId() {
        return loanAppliId;
    }

    public void setLoanAppliId(Long loanAppliId) {
        this.loanAppliId = loanAppliId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    public String getDataLabelTypes() {
        return dataLabelTypes;
    }

    public void setDataLabelTypes(String dataLabelTypes) {
        this.dataLabelTypes = dataLabelTypes;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
