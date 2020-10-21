package com.scistor.develop.data.entity;

import com.scistor.develop.data.ParentEntity;

import java.util.Date;

public class LoanDemand extends ParentEntity {

    public String loanChanne;     //贷款渠道
    public String loanTimeLimit;  //贷款期限
    public String loanAmount;     //贷款额度
    public String guaranteeMode;  //担保方式
    public String purpose;        //用途
    public Integer num;           //贷款次数
    public String applicant;      //申请人
    public String orgNumber;      //组织机构代码
    public String companyAddress; //注册地址
    public String type;           //类型,1上线，2下线
    public Long id;
    public String usercode;             //登陆用户编码
    public String companyName;          //  '企业、机构、银行名称',
    public String companyId;            //  '企业、机构、银行id',
    public String companyType;          // '类型：1企业，2金融机构，3银行，4金融局',
    public String createrUser;          // '数据创建人，申请人',
    public Date createrTime;            // '数据创建时间',
    public Date updateTime;             // '数据修改时间',
    public Integer deleteFlag;          //'1:删除，0：未删除',
    public Integer focusNum;            //被关注次数
    public Integer acceptAmount;        //受理次数---关联查询出来的
    public String nameIntendedBank;     //意向银行---即金融机构ID

    public String getNameIntendedBank() {
        return nameIntendedBank;
    }

    public void setNameIntendedBank(String nameIntendedBank) {
        this.nameIntendedBank = nameIntendedBank;
    }

    public Integer getAcceptAmount() {
        return acceptAmount;
    }

    public void setAcceptAmount(Integer acceptAmount) {
        this.acceptAmount = acceptAmount;
    }

    public Integer getFocusNum() {
        return focusNum;
    }

    public void setFocusNum(Integer focusNum) {
        this.focusNum = focusNum;
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

    public String getLoanChanne() {
        return loanChanne;
    }

    public void setLoanChanne(String loanChanne) {
        this.loanChanne = loanChanne;
    }

    public String getLoanTimeLimit() {
        return loanTimeLimit;
    }

    public void setLoanTimeLimit(String loanTimeLimit) {
        this.loanTimeLimit = loanTimeLimit;
    }

    public String getGuaranteeMode() {
        return guaranteeMode;
    }

    public void setGuaranteeMode(String guaranteeMode) {
        this.guaranteeMode = guaranteeMode;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getOrgNumber() {
        return orgNumber;
    }

    public void setOrgNumber(String orgNumber) {
        this.orgNumber = orgNumber;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
