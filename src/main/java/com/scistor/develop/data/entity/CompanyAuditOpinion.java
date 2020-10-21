package com.scistor.develop.data.entity;

import com.scistor.develop.data.ParentEntity;

import java.util.Date;

public class CompanyAuditOpinion extends ParentEntity {

    public Long id;

    public String companyId;            //企业ID

    public String companyName;          //企业名称

    public String companyType;          //企业类型

    public Date createrTime;           //数据创建时间

    public Date updateTime;            //数据修改时间

    public Integer deleteFlag;          //'1:删除，0：未删除',

    public String checkMsg;              //意见

    public String usercode;             //用户id

    public Long companyInfoId;          //企业注册表ID

    public String createrUser;          //

    public String getCreaterUser() {
        return createrUser;
    }

    public void setCreaterUser(String createrUser) {
        this.createrUser = createrUser;
    }

    public Long getCompanyInfoId() {
        return companyInfoId;
    }

    public void setCompanyInfoId(Long companyInfoId) {
        this.companyInfoId = companyInfoId;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
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

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getCheckMsg() {
        return checkMsg;
    }

    public void setCheckMsg(String checkMsg) {
        this.checkMsg = checkMsg;
    }
}
