package com.scistor.develop.data.entity;

import com.scistor.develop.data.ParentEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.scistor.develop.tools.SQLBlockAttribute.SQL_CK_SPLIT_STR;

/**
 * 股权审核表--查询表
 */
public class EquityAuditList extends ParentEntity {
    private Long id;
    private String companyName;
    private String companyId;
    private String companyInvestmentStage;
    private String applynum;
    private Date createrTime;
    private Date updateTime;
    private String deleteFlag;
    private String acceptCompanyId;
    private String acceptCompanyName;
    private Date loanTime;
    private String reviewProgress;
    private String checkType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCompanyInvestmentStage() {
        return companyInvestmentStage;
    }

    public void setCompanyInvestmentStage(String companyInvestmentStage) {
        this.companyInvestmentStage = companyInvestmentStage;
    }

    public String getApplynum() {
        return applynum;
    }

    public void setApplynum(String applynum) {
        this.applynum = applynum;
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

    public String getAcceptCompanyId() {
        return acceptCompanyId;
    }

    public void setAcceptCompanyId(String acceptCompanyId) {
        this.acceptCompanyId = acceptCompanyId;
    }

    public String getAcceptCompanyName() {
        return acceptCompanyName;
    }

    public void setAcceptCompanyName(String acceptCompanyName) {
        this.acceptCompanyName = acceptCompanyName;
    }

    public Date getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(Date loanTime) {
        this.loanTime = loanTime;
    }

    public String getReviewProgress() {
        return reviewProgress;
    }

    public void setReviewProgress(String reviewProgress) {
        this.reviewProgress = reviewProgress;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }
}
