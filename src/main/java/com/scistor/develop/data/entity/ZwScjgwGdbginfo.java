package com.scistor.develop.data.entity;

import java.util.Date;

/**
 * 市场监管委股东变更信息表
 * 表：scjgw_gdbginfo
 */
public class ZwScjgwGdbginfo {

    public String entId;           //市场监管委股东变更信息表

    public String inv;             //股东名称

    public String transAmprBefore; //变更前股权比例

    public String transAmprAfter;  //变更后股权比例

    public String altDate;           //股权变更日期

    public String ancheYear;       //年报年度

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getInv() {
        return inv;
    }

    public void setInv(String inv) {
        this.inv = inv;
    }

    public String getAncheYear() {
        return ancheYear;
    }

    public void setAncheYear(String ancheYear) {
        this.ancheYear = ancheYear;
    }

    public String getTransAmprBefore() {
        return transAmprBefore;
    }

    public void setTransAmprBefore(String transAmprBefore) {
        this.transAmprBefore = transAmprBefore;
    }

    public String getTransAmprAfter() {
        return transAmprAfter;
    }

    public void setTransAmprAfter(String transAmprAfter) {
        this.transAmprAfter = transAmprAfter;
    }

    public String getAltDate() {
        return altDate;
    }

    public void setAltDate(String altDate) {
        this.altDate = altDate;
    }
}