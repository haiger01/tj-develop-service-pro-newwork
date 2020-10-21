package com.scistor.develop.data.entity;

import java.util.Date;

/**
 * 高院结案信息
 * 表：gf_case_end_info
 */
public class ZwGfCaseEndInfo {

    public String caseId;         //案件标识

    public String caseArea;        //案件区域

    public String caseNumber;       //案号

    public String comNumber;        //企业编号

    public String comName;          //企业名称

    public String legPerIdNumber;   //法人证件号

    public String legPerson;        //法定代表人

    public String litStatus;        //诉讼地位

    public String cause;            //案由

    public String caseEndDate;        //结案日期

    public String caseEndMode;      //结案方式

    public String judResult;        //判决结果（刑事）



    public String getCaseArea() {
        return caseArea;
    }

    public void setCaseArea(String caseArea) {
        this.caseArea = caseArea;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getComNumber() {
        return comNumber;
    }

    public void setComNumber(String comNumber) {
        this.comNumber = comNumber;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getLegPerIdNumber() {
        return legPerIdNumber;
    }

    public void setLegPerIdNumber(String legPerIdNumber) {
        this.legPerIdNumber = legPerIdNumber;
    }

    public String getLegPerson() {
        return legPerson;
    }

    public void setLegPerson(String legPerson) {
        this.legPerson = legPerson;
    }

    public String getLitStatus() {
        return litStatus;
    }

    public void setLitStatus(String litStatus) {
        this.litStatus = litStatus;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
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

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getCaseEndDate() {
        return caseEndDate;
    }

    public void setCaseEndDate(String caseEndDate) {
        this.caseEndDate = caseEndDate;
    }
}