package com.scistor.develop.data.entity;

import com.scistor.develop.data.ParentEntity;

import java.util.Date;

public class Insurance extends ParentEntity {

    public String tel;              //联系方式

    public String proName;          //产品标题、名称
    public String characteristic;   //产品特点
    public String appCustomer;      //产品客户
    public String appCondition;     //申请条件
    public String checkType;        //发布审核状态 0，审核中 1，审核通过 2 ，审核拒绝
    public String checkUser;        //发布审核员
    public String checkMsg;         //审核备注
    public String checkTime;        //审核时间
    public String type;             //当前状态 0下线，1上线
    public Long id;
    public String usercode;             //登陆用户编码
    public String companyName;          //  '企业、机构、银行名称',
    public String companyId;            //  '企业、机构、银行id',
    public String companyType;          // '类型：1企业，2金融机构，3银行，4金融局',
    public String createrUser;          // '数据创建人，申请人',
    public Date createrTime;            // '数据创建时间',
    public Date updateTime;             // '数据修改时间',
    public Integer deleteFlag;          // '1:删除，0：未删除',

    public String loanRateMax;      //最大利率
    public String loanRateMin;      //最小利率最大值
    public String loanRate;         //利率辅助字段


    public String loanRateStr;          //展示利率
    public String loanAmountStr;        //展示金额
    public String loanTimeLimitStr;    //展示期限
    public String proInt;           //产品介绍

    public String proContactPersonPhone;         //利率辅助字段
    public String proContactPerson;         //利率辅助字段
    public String productAccessoriesUrl;//附件地址

    public String getProContactPersonPhone() {
        return proContactPersonPhone;
    }

    public void setProContactPersonPhone(String proContactPersonPhone) {
        this.proContactPersonPhone = proContactPersonPhone;
    }

    public String getProContactPerson() {
        return proContactPerson;
    }

    public void setProContactPerson(String proContactPerson) {
        this.proContactPerson = proContactPerson;
    }

    public String getProductAccessoriesUrl() {
        return productAccessoriesUrl;
    }

    public void setProductAccessoriesUrl(String productAccessoriesUrl) {
        this.productAccessoriesUrl = productAccessoriesUrl;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public String getAppCustomer() {
        return appCustomer;
    }

    public void setAppCustomer(String appCustomer) {
        this.appCustomer = appCustomer;
    }

    public String getAppCondition() {
        return appCondition;
    }

    public void setAppCondition(String appCondition) {
        this.appCondition = appCondition;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }

    public String getCheckMsg() {
        return checkMsg;
    }

    public void setCheckMsg(String checkMsg) {
        this.checkMsg = checkMsg;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getCreaterUser() {
        return createrUser;
    }

    public void setCreaterUser(String createrUser) {
        this.createrUser = createrUser;
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

    public String getLoanRateMax() {
        return loanRateMax;
    }

    public void setLoanRateMax(String loanRateMax) {
        this.loanRateMax = loanRateMax;
    }

    public String getLoanRateMin() {
        return loanRateMin;
    }

    public void setLoanRateMin(String loanRateMin) {
        this.loanRateMin = loanRateMin;
    }

    public String getLoanRate() {
        return loanRate;
    }

    public void setLoanRate(String loanRate) {
        this.loanRate = loanRate;
    }

    public String getLoanRateStr() {
        return loanRateStr;
    }

    public void setLoanRateStr(String loanRateStr) {
        this.loanRateStr = loanRateStr;
    }

    public String getLoanAmountStr() {
        return loanAmountStr;
    }

    public void setLoanAmountStr(String loanAmountStr) {
        this.loanAmountStr = loanAmountStr;
    }

    public String getLoanTimeLimitStr() {
        return loanTimeLimitStr;
    }

    public void setLoanTimeLimitStr(String loanTimeLimitStr) {
        this.loanTimeLimitStr = loanTimeLimitStr;
    }

    public String getProInt() {
        return proInt;
    }

    public void setProInt(String proInt) {
        this.proInt = proInt;
    }
}
