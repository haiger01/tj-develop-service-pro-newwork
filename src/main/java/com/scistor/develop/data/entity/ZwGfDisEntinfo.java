package com.scistor.develop.data.entity;

/**
 * 高院失信企业信息
 * 表：gf_dis_entinfo
 */
public class ZwGfDisEntinfo {
    
    public String exedComName;          //被执行企业名称

    public String orgCode;              //组织机构代码

    public String legPerson;            //法定代表人

    public String exedComAddress;       //被执行企业地址

    public String exeCaseNumber;        //执行案号

    public String exeBasSymbol;         //执行依据文号(裁判书名称)

    public String exeCase;              //执行案由

    public String subAmount;            //标的金额

    public String getExedComName() {
        return exedComName;
    }

    public void setExedComName(String exedComName) {
        this.exedComName = exedComName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getLegPerson() {
        return legPerson;
    }

    public void setLegPerson(String legPerson) {
        this.legPerson = legPerson;
    }

    public String getExedComAddress() {
        return exedComAddress;
    }

    public void setExedComAddress(String exedComAddress) {
        this.exedComAddress = exedComAddress;
    }

    public String getExeCaseNumber() {
        return exeCaseNumber;
    }

    public void setExeCaseNumber(String exeCaseNumber) {
        this.exeCaseNumber = exeCaseNumber;
    }

    public String getExeBasSymbol() {
        return exeBasSymbol;
    }

    public void setExeBasSymbol(String exeBasSymbol) {
        this.exeBasSymbol = exeBasSymbol;
    }

    public String getExeCase() {
        return exeCase;
    }

    public void setExeCase(String exeCase) {
        this.exeCase = exeCase;
    }

    public String getSubAmount() {
        return subAmount;
    }

    public void setSubAmount(String subAmount) {
        this.subAmount = subAmount;
    }
}