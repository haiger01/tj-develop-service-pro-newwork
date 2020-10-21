package com.scistor.develop.data.entity;


import com.scistor.develop.data.ParentEntity;

import java.util.Date;

public class NewsApply {

    public Long id;
    public Long newsId;       //新闻id news_doc
    public String companyName;          //企业名称
    public String companyId;            //企业id
    public String companyType;          //
    public String applyName;            //报名名称
    public String applyPhone;           //报名电话
    public String applyCompany;         //报名企业
    public Integer conferenceNumber;    //参会人员人数
    public String conferenceName;       // 参会人员
    public String cultivateState;       // 培训状态：1-未开始，2-开始，3-结束
    public String feedbackType;         // 反馈类型：1到5
    public String feedbackIdea;         // 反馈意见
    public Date createrTime;            // 数据创建时间
    public Date updateTime;             // 数据修改时间
    public Integer watchFlag;           //用户是否观看：0-否，1-是
    public Integer quitFlag;            //用户是否退出：0-否，1-是
    public String createrUser;
    public String usercode;             //


    public String tittle;               //标题（数据库无）
    public String cultivateTime;        //培训时间（数据库无）

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
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

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getApplyPhone() {
        return applyPhone;
    }

    public void setApplyPhone(String applyPhone) {
        this.applyPhone = applyPhone;
    }

    public String getApplyCompany() {
        return applyCompany;
    }

    public void setApplyCompany(String applyCompany) {
        this.applyCompany = applyCompany;
    }

    public Integer getConferenceNumber() {
        return conferenceNumber;
    }

    public void setConferenceNumber(Integer conferenceNumber) {
        this.conferenceNumber = conferenceNumber;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public String getCultivateState() {
        return cultivateState;
    }

    public void setCultivateState(String cultivateState) {
        this.cultivateState = cultivateState;
    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getFeedbackIdea() {
        return feedbackIdea;
    }

    public void setFeedbackIdea(String feedbackIdea) {
        this.feedbackIdea = feedbackIdea;
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

    public Integer getWatchFlag() {
        return watchFlag;
    }

    public void setWatchFlag(Integer watchFlag) {
        this.watchFlag = watchFlag;
    }

    public Integer getQuitFlag() {
        return quitFlag;
    }

    public void setQuitFlag(Integer quitFlag) {
        this.quitFlag = quitFlag;
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

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getCultivateTime() {
        return cultivateTime;
    }

    public void setCultivateTime(String cultivateTime) {
        this.cultivateTime = cultivateTime;
    }
}
