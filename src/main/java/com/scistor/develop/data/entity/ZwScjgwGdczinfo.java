package com.scistor.develop.data.entity;

/**
 * 市场监管委股东出资信息表
 * 表：scjgw_gdczinfo
 */
public class ZwScjgwGdczinfo {

    public String entId;            //市场监管委股东出资信息表

    public String inv;              //股东名称

    public String conDate;          //出资时间

    public String conFrom;          //出资方式

    public String conNam;           //出资金额

    public String invType;          //股东发起人/类型 （实缴/认缴）

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

    public String getConDate() {
        return conDate;
    }

    public void setConDate(String conDate) {
        this.conDate = conDate;
    }

    public String getConFrom() {
        return conFrom;
    }

    public void setConFrom(String conFrom) {
        this.conFrom = conFrom;
    }

    public String getConNam() {
        return conNam;
    }

    public void setConNam(String conNam) {
        this.conNam = conNam;
    }

    public String getInvType() {
        return invType;
    }

    public void setInvType(String invType) {
        this.invType = invType;
    }
}