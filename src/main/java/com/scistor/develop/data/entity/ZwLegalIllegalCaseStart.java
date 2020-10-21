package com.scistor.develop.data.entity;

import java.util.Date;

/**
 *  4.企业法定代表人违法信息（立案）
 * 表：legal_illegal_case_start
 */
public class ZwLegalIllegalCaseStart {
    
    public String litName;          //当事人姓名

    public String idNumber;         //身份证号

    public String gender;           //性别:0-未知的性别,1-男,2-女,9-未说明的性别

    public String position;         //职务

    public String caseCategory;     //案件类别

    public String caseSource;       //案件来源

    public String caseNumber;       //案号

    public String caseStartDate;      //立案日期

    public String cause;            //案由


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

    public String getCaseCategory() {
        return caseCategory;
    }

    public void setCaseCategory(String caseCategory) {
        this.caseCategory = caseCategory;
    }

    public String getCaseSource() {
        return caseSource;
    }

    public void setCaseSource(String caseSource) {
        this.caseSource = caseSource;
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

    public String getCaseStartDate() {
        return caseStartDate;
    }

    public void setCaseStartDate(String caseStartDate) {
        this.caseStartDate = caseStartDate;
    }
}