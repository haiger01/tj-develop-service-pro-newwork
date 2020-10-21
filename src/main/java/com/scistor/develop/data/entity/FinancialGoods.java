package com.scistor.develop.data.entity;

import com.scistor.develop.data.ParentEntity;

import java.math.BigDecimal;
import java.util.Date;

public class FinancialGoods extends ParentEntity {
    public String tel;              //联系方式
    public String loanChanne;       //贷款渠道
    public String guaranteeMode;    //担保方式

    public String proName;          //产品标题、名称
    public String goodsType;        //产品类型
    public String characteristic;   //产品特点
    public String appCustomer;      //产品客户
    public String appCondition;     //申请条件
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
    public String fixedTel;             // 固定电话

    public Long success;          //成功次数
    public Long fail;             //失败次数
    public Long applnum;          //申请次数
    public String loanTimeLimitMax; //贷款期限最大值
    public String loanTimeLimitMin; //贷款期限最小值
    public String loanAmountMax;    //贷款额度最大值
    public String loanAmountMin;    //贷款额度最小值
    public String loanRateMax;      //最大利率
    public String loanRateMin;      //最小利率最大值
    public String loanRate;         //利率辅助字段


    public String loanRateStr;          //展示利率
    public String loanAmountStr;        //展示金额
    public String loanTimeLimitStr;    //展示期限
    public String proInt;           //产品介绍

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

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoCoverUrl() {
        return videoCoverUrl;
    }

    public void setVideoCoverUrl(String videoCoverUrl) {
        this.videoCoverUrl = videoCoverUrl;
    }

    public String getLoanRateStr() {
        return loanRateStr;
    }

    public String getLoanAmountStr() {
        return loanAmountStr;
    }

    public String getLoanTimeLimitStr() {
        return loanTimeLimitStr;
    }

    public String getProInt() {
//        if (proInt == null) return proInt;
//        if (proInt.indexOf("\n补充：") > -1) return proInt;
//
//        if (loanRateStr != null || loanAmountStr != null || loanTimeLimitStr != null)
//            proInt += "\n补充：";
//        if (loanRateStr != null)
//            proInt += loanRateStr == null ? "" : "\n利率" + loanRateStr;
//        if (loanAmountStr != null)
//            proInt += loanAmountStr == null ? "" : "\n额度" + loanAmountStr;
//        if (loanTimeLimitStr != null)
//            proInt += loanTimeLimitStr == null ? "" : "\n期限" + loanTimeLimitStr;
        return proInt;

    }

    public void setLoanRateStr(String loanRateStr) {
        this.loanRateStr = loanRateStr;
    }

    public void setLoanAmountStr(String loanAmountStr) {
        this.loanAmountStr = loanAmountStr;
    }

    public void setLoanTimeLimitStr(String loanTimeLimitStr) {
        this.loanTimeLimitStr = loanTimeLimitStr;
    }

    public void setProInt(String proInt) {
        this.proInt = proInt;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLoanChanne() {
        return loanChanne;
    }

    public void setLoanChanne(String loanChanne) {
        this.loanChanne = loanChanne;
    }

    public String getLoanTimeLimitMax() {
        return loanTimeLimitMax;
    }

    public void setLoanTimeLimitMax(String loanTimeLimitMax) {
        this.loanTimeLimitMax = loanTimeLimitMax;
    }

    public String getLoanTimeLimitMin() {
        return loanTimeLimitMin;
    }

    public void setLoanTimeLimitMin(String loanTimeLimitMin) {
        this.loanTimeLimitMin = loanTimeLimitMin;
    }

    public String getLoanAmountMax() {
        return loanAmountMax;
    }

    public void setLoanAmountMax(String loanAmountMax) {
        this.loanAmountMax = loanAmountMax;
    }

    public String getLoanAmountMin() {
        return loanAmountMin;
    }

    public void setLoanAmountMin(String loanAmountMin) {
        this.loanAmountMin = loanAmountMin;
    }

    public String getLoanRateMax() {
        return loanRateMax;
    }

    public void setLoanRateMax(String loanRateMax) {
        this.loanRateMax = loanRateMax;
    }

    public String getLoanRateMin() {
        return loanRateMin;
    }

    public void setLoanRateMin(String loanRateMin) {
        this.loanRateMin = loanRateMin;
    }

    public String getGuaranteeMode() {
        return guaranteeMode;
    }

    public void setGuaranteeMode(String guaranteeMode) {
        this.guaranteeMode = guaranteeMode;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public String getAppCustomer() {
        return appCustomer;
    }

    public void setAppCustomer(String appCustomer) {
        this.appCustomer = appCustomer;
    }

    public String getAppCondition() {
        return appCondition;
    }

    public void setAppCondition(String appCondition) {
        this.appCondition = appCondition;
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

    public String getFixedTel() {
        return fixedTel;
    }

    public void setFixedTel(String fixedTel) {
        this.fixedTel = fixedTel;
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


    public String getLoanRate() {
        return loanRate;
    }

    public void setLoanRate(String loanRate) {
        this.loanRate = loanRate;
    }

}
