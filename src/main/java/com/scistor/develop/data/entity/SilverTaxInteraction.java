package com.scistor.develop.data.entity;

import com.scistor.develop.data.ParentEntity;

import java.util.Date;

public class SilverTaxInteraction extends ParentEntity {

    public Long id;

    public Integer serviceNumber;                   //服务企业数量

    public Integer cumulativeCredit;                //授信笔数

    public String totalCumulativeCreditMoney;       //授信总金额

    public Integer deleteFlag;                      //删除状态

    public Date createrTime;

    public Date updateTime;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(Integer serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public Integer getCumulativeCredit() {
        return cumulativeCredit;
    }

    public void setCumulativeCredit(Integer cumulativeCredit) {
        this.cumulativeCredit = cumulativeCredit;
    }

    public String getTotalCumulativeCreditMoney() {
        return totalCumulativeCreditMoney;
    }

    public void setTotalCumulativeCreditMoney(String totalCumulativeCreditMoney) {
        this.totalCumulativeCreditMoney = totalCumulativeCreditMoney;
    }
}
