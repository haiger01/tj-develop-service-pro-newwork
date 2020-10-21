package com.scistor.develop.data.entity;

import com.scistor.develop.data.ParentEntity;

import java.util.Date;

/**
 * 项目+团队名录实体类
 */
public class ProjectTeamDirectory extends ParentEntity {

    public Long id;//主键
    public String createrCompanyId;//信息创建人ID
    public String createrCompanyName;//信息创建人名称
    public String createrUser;//信息创建人用户名
    public String createrUsercode;//信息创建人统一认证平台ID
    public String createrCompanyType;//创建人类型---级别---权限
    public Date createrTime;//数据创建时间
    public Date updateTime;//修改数据时间
    public String deleteFlag;//数据删除状态0：不删除，1：删除
    public String companyName;//企业名称
    public String companyContacts;//企业联系人
    public String companyContactsPhone;//企业联系人手机号
    public Integer companyRegistrationFlag;//企业注册状态
    public String specialistName;//专员名称
    public String specialistContactsPhone;//专员手机号
    public Integer specialistRegistrationFlag;//专员注册状态

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreaterCompanyId() {
        return createrCompanyId;
    }

    public void setCreaterCompanyId(String createrCompanyId) {
        this.createrCompanyId = createrCompanyId;
    }

    public String getCreaterCompanyName() {
        return createrCompanyName;
    }

    public void setCreaterCompanyName(String createrCompanyName) {
        this.createrCompanyName = createrCompanyName;
    }

    public String getCreaterUser() {
        return createrUser;
    }

    public void setCreaterUser(String createrUser) {
        this.createrUser = createrUser;
    }

    public String getCreaterUsercode() {
        return createrUsercode;
    }

    public void setCreaterUsercode(String createrUsercode) {
        this.createrUsercode = createrUsercode;
    }

    public String getCreaterCompanyType() {
        return createrCompanyType;
    }

    public void setCreaterCompanyType(String createrCompanyType) {
        this.createrCompanyType = createrCompanyType;
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

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyContacts() {
        return companyContacts;
    }

    public void setCompanyContacts(String companyContacts) {
        this.companyContacts = companyContacts;
    }

    public String getCompanyContactsPhone() {
        return companyContactsPhone;
    }

    public void setCompanyContactsPhone(String companyContactsPhone) {
        this.companyContactsPhone = companyContactsPhone;
    }

    public Integer getCompanyRegistrationFlag() {
        return companyRegistrationFlag;
    }

    public void setCompanyRegistrationFlag(Integer companyRegistrationFlag) {
        this.companyRegistrationFlag = companyRegistrationFlag;
    }

    public String getSpecialistName() {
        return specialistName;
    }

    public void setSpecialistName(String specialistName) {
        this.specialistName = specialistName;
    }

    public String getSpecialistContactsPhone() {
        return specialistContactsPhone;
    }

    public void setSpecialistContactsPhone(String specialistContactsPhone) {
        this.specialistContactsPhone = specialistContactsPhone;
    }

    public Integer getSpecialistRegistrationFlag() {
        return specialistRegistrationFlag;
    }

    public void setSpecialistRegistrationFlag(Integer specialistRegistrationFlag) {
        this.specialistRegistrationFlag = specialistRegistrationFlag;
    }
}
