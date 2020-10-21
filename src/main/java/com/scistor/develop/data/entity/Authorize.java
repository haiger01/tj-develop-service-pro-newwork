package com.scistor.develop.data.entity;

import java.util.Date;

public class Authorize {
   public int id;                     //主键
   public String companyName;         //企业、机构、银行名称
   public String companyId;           //企业、机构、银行id
   public String companyType;         //类型：1企业，2金融机构，3银行，4金融局
   public String createrUser;         //数据创建人，申请人
   public Date createrTime;           //数据创建时间
   public Date updateTime;            //数据修改时间
   public String deleteFlag;          //1:删除，0：未删除
   public String type;                //授权类型：1，查看企业数据，2其他
   public String code;                //授权码
   public Date endTime;               //授权到期时间
   public String authorizeType;       //授权状态：1有效，2失效
   public String authorizeCompanyId;  //可查询的企业编码
   public String appid;               //申请表的主键


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
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

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getAuthorizeType() {
        return authorizeType;
    }

    public void setAuthorizeType(String authorizeType) {
        this.authorizeType = authorizeType;
    }

    public String getAuthorizeCompanyId() {
        return authorizeCompanyId;
    }

    public void setAuthorizeCompanyId(String authorizeCompanyId) {
        this.authorizeCompanyId = authorizeCompanyId;
    }
}
