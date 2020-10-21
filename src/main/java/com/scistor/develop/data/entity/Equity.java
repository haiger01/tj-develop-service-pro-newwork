package com.scistor.develop.data.entity;

import com.scistor.develop.data.ParentEntity;

import java.util.Date;

public class Equity extends ParentEntity {
    public String tel;              //联系方式

    public String proName;          //产品标题、名称
    public String checkType;        //发布审核状态 0，审核中 1，审核通过 2 ，审核拒绝
    public String checkUser;        //发布审核员
    public String checkMsg;         //审核备注
    public String checkTime;        //审核时间
    public String type;             //当前状态 0下线，1上线
    public Long id;
    public String usercode;             //登陆用户编码
    public String companyName;          //  '企业、机构、银行名称',
    public String companyId;            //  '企业、机构、银行id',
    public String companyType;          // '类型：1企业，2金融机构，3银行，4金融局',
    public String createrUser;          // '数据创建人，申请人',
    public Date createrTime;            // '数据创建时间',
    public Date updateTime;             // '数据修改时间',
    public Integer deleteFlag;          // '1:删除，0：未删除',

    public Long success;          //成功次数
    public Long fail;             //失败次数
    public Long applnum;          //申请次数
    public String remarks;          //备注
    public String investmentIndustry;//投资行业
    public String investmentStage;//投资阶段
    public String videoUrl;//视频路径

    public String videoCoverUrl;//视频封面

    public String proContactPerson;//联系人

    public String proContactPersonPhone;//联系方式

    public String getProContactPerson() {
        return proContactPerson;
    }

    public void setProContactPerson(String proContactPerson) {
        this.proContactPerson = proContactPerson;
    }

    public String getProContactPersonPhone() {
        return proContactPersonPhone;
    }

    public void setProContactPersonPhone(String proContactPersonPhone) {
        this.proContactPersonPhone = proContactPersonPhone;
    }

    public String getVideoCoverUrl() {
        return videoCoverUrl;
    }

    public void setVideoCoverUrl(String videoCoverUrl) {
        this.videoCoverUrl = videoCoverUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }

    public String getCheckMsg() {
        return checkMsg;
    }

    public void setCheckMsg(String checkMsg) {
        this.checkMsg = checkMsg;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getSuccess() {
        return success;
    }

    public void setSuccess(Long success) {
        this.success = success;
    }

    public Long getFail() {
        return fail;
    }

    public void setFail(Long fail) {
        this.fail = fail;
    }

    public Long getApplnum() {
        return applnum;
    }

    public void setApplnum(Long applnum) {
        this.applnum = applnum;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getInvestmentIndustry() {
        return investmentIndustry;
    }

    public void setInvestmentIndustry(String investmentIndustry) {
        this.investmentIndustry = investmentIndustry;
    }

    public String getInvestmentStage() {
        return investmentStage;
    }

    public void setInvestmentStage(String investmentStage) {
        this.investmentStage = investmentStage;
    }
}
