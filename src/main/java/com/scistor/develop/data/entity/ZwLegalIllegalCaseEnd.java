package com.scistor.develop.data.entity;

import java.util.Date;

/**
 * 企业法定代表人违法信息（结案）
 * 表：legal_illegal_case_end
 */
public class ZwLegalIllegalCaseEnd {
    
    public String litName;         //当事人姓名

    public String idNumber;        //身份证号

    public String gender;          //性别:0-未知的性别,1-男,2-女,9-未说明的性别

    public String position;        //职务

    public String litStatus;       //诉讼地位

    public String caseNumber;      //案号

    public String caseEndDate;       //结案日期

    public String cause;           //案由

    public String caseEndSubject;  //结案标的

    public String caseEndMode;     //结案方式

    public String judResult;       //判决结果（刑事）

    public String getLitName() {
        return litName;
    }

    public void setLitName(String litName) {
        this.litName = litName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLitStatus() {
        return litStatus;
    }

    public void setLitStatus(String litStatus) {
        this.litStatus = litStatus;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getCaseEndSubject() {
        return caseEndSubject;
    }

    public void setCaseEndSubject(String caseEndSubject) {
        this.caseEndSubject = caseEndSubject;
    }

    public String getCaseEndMode() {
        return caseEndMode;
    }

    public void setCaseEndMode(String caseEndMode) {
        this.caseEndMode = caseEndMode;
    }

    public String getJudResult() {
        return judResult;
    }

    public void setJudResult(String judResult) {
        this.judResult = judResult;
    }

    public String getCaseEndDate() {
        return caseEndDate;
    }

    public void setCaseEndDate(String caseEndDate) {
        this.caseEndDate = caseEndDate;
    }
}