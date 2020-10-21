package com.scistor.develop.data.entity;

import com.scistor.develop.data.ParentEntity;

import java.util.Date;

public class FocusNeeds extends ParentEntity {
    public String demandId;     //需求id
    public String tel;          //需求练习方式
    public String goodsId;      //推荐产品id
    public String goodsName;    //推荐产品名称
    public String comment;      //备注
    public String focusType;    //意向类型 1.贷款，2担保
    public Long id;
    public String usercode;             //登陆用户编码
    public String companyName;          //  '企业、机构、银行名称',
    public String companyId;            //  '企业、机构、银行id',
    public String companyType;          // '类型：1企业，2金融机构，3银行，4金融局',
    public String createrUser;          // '数据创建人，申请人',
    public Date createrTime;            // '数据创建时间',
    public Date updateTime;             // '数据修改时间',
    public Integer deleteFlag;          //'1:删除，0：未删除',
    public String focusCompanyId;       //受理企业ID

    public String getFocusCompanyId() {
        return focusCompanyId;
    }

    public void setFocusCompanyId(String focusCompanyId) {
        this.focusCompanyId = focusCompanyId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFocusType() {
        return focusType;
    }

    public void setFocusType(String focusType) {
        this.focusType = focusType;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
