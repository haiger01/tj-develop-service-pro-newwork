package com.scistor.develop.data.entity;

import com.scistor.develop.data.ParentEntity;

import java.math.BigDecimal;
import java.util.Date;

public class Guarantee extends ParentEntity {
    public String guaranteeMode;    //担保方式
    public String checkType;        //发布审核状态 0，审核中 1，审核通过 2 ，审核拒绝
    public String checkUser;        //发布审核员
    public String checkMsg;         //审核备注
    public String checkTime;        //审核时间
    public String type;             //当前状态 0下线，1上线
    public String policyFlag;       //是否政策性产品1是，0否
    public String rmbFlag;          //是否人民币产品1是0否
    public String region;           //适用设区市
    public String goodsClass;       //产品分类
    public String netDirectFlag;    //是否网贷直联1是，0否
    public String generalFlag;      //是否通用产品
    public String goodsCategory;    //贷款类别
    public String conditions;       //申请条件
    public String material;         //申请需提交材料
    public String proName;          //产品名称
    public Long success;          //成功次数
    public Long fail;             //失败次数
    public Long applnum;            //申请次数

    public Long id;
    public String usercode;             //登陆用户编码
    public String companyName;          //  '企业、机构、银行名称',
    public String companyId;            //  '企业、机构、银行id',
    public String companyType;          // '类型：1企业，2金融机构，3银行，4金融局',
    public String createrUser;          // '数据创建人，申请人',
    public Date createrTime;            // '数据创建时间',
    public Date updateTime;             // '数据修改时间',
    public Integer deleteFlag;          //'1:删除，0：未删除',
    public String tel;              //联系方式
    public String fixedTel;             // 固定电话comAudList

    public String guaranteeRate;       //利率辅助字段
    public String guaranteeRateMax;    //参考担保费最大值
    public String guaranteeRateMin;    //参考担保费最小值
    public String guaranteeLineMax;    //融资担保额度最大值
    public String guaranteeLineMin;    //融资担保额度最小值
    public String guaranteeDateMax;    //融资担保期限最大值
    public String guaranteeDateMin;    //融资担保期限最小值
    public String guaranteeRateStr;    //展示利率
    public String guaranteeLineStr;    //展示金额
    public String guaranteeDateStr;    //展示期限
    public String goodsTrait;           //产品特点
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

    public String getGuaranteeRateStr() {
        return guaranteeRateStr;
    }

    public String getGuaranteeLineStr() {
        return guaranteeLineStr;
    }

    public String getGuaranteeDateStr() {
        return guaranteeDateStr;
    }

    public String getGoodsTrait() {
//        if (goodsTrait == null) return goodsTrait;
//        if (goodsTrait.indexOf("\n补充：") > -1) return goodsTrait;
//
//        if (guaranteeRateStr != null || guaranteeLineStr != null || guaranteeDateStr != null)
//            goodsTrait += "\n补充：";
//        if (guaranteeRateStr != null)
//            goodsTrait += guaranteeRateStr == null ? "" : "\n 利率" + guaranteeRateStr;
//        if (guaranteeLineStr != null)
//            goodsTrait += guaranteeLineStr == null ? "" : "\n 额度" + guaranteeLineStr;
//        if (guaranteeDateStr != null)
//            goodsTrait += guaranteeDateStr == null ? "" : "\n 期限" + guaranteeDateStr;
        return goodsTrait;
    }


    public String getGuaranteeMode() {
        return guaranteeMode;
    }

    public void setGuaranteeMode(String guaranteeMode) {
        this.guaranteeMode = guaranteeMode;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setGuaranteeRateStr(String guaranteeRateStr) {
        this.guaranteeRateStr = guaranteeRateStr;
    }

    public void setGuaranteeLineStr(String guaranteeLineStr) {
        this.guaranteeLineStr = guaranteeLineStr;
    }

    public void setGuaranteeDateStr(String guaranteeDateStr) {
        this.guaranteeDateStr = guaranteeDateStr;
    }

    public void setGoodsTrait(String goodsTrait) {
        this.goodsTrait = goodsTrait;
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

    public String getPolicyFlag() {
        return policyFlag;
    }

    public void setPolicyFlag(String policyFlag) {
        this.policyFlag = policyFlag;
    }

    public String getRmbFlag() {
        return rmbFlag;
    }

    public void setRmbFlag(String rmbFlag) {
        this.rmbFlag = rmbFlag;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getGoodsClass() {
        return goodsClass;
    }

    public void setGoodsClass(String goodsClass) {
        this.goodsClass = goodsClass;
    }

    public String getNetDirectFlag() {
        return netDirectFlag;
    }

    public void setNetDirectFlag(String netDirectFlag) {
        this.netDirectFlag = netDirectFlag;
    }

    public String getGeneralFlag() {
        return generalFlag;
    }

    public void setGeneralFlag(String generalFlag) {
        this.generalFlag = generalFlag;
    }

    public String getGoodsCategory() {
        return goodsCategory;
    }

    public void setGoodsCategory(String goodsCategory) {
        this.goodsCategory = goodsCategory;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFixedTel() {
        return fixedTel;
    }

    public void setFixedTel(String fixedTel) {
        this.fixedTel = fixedTel;
    }

    public String getGuaranteeRate() {
        return guaranteeRate;
    }

    public void setGuaranteeRate(String guaranteeRate) {
        this.guaranteeRate = guaranteeRate;
    }

    public String getGuaranteeRateMax() {
        return guaranteeRateMax;
    }

    public void setGuaranteeRateMax(String guaranteeRateMax) {
        this.guaranteeRateMax = guaranteeRateMax;
    }

    public String getGuaranteeRateMin() {
        return guaranteeRateMin;
    }

    public void setGuaranteeRateMin(String guaranteeRateMin) {
        this.guaranteeRateMin = guaranteeRateMin;
    }

    public String getGuaranteeLineMax() {
        return guaranteeLineMax;
    }

    public void setGuaranteeLineMax(String guaranteeLineMax) {
        this.guaranteeLineMax = guaranteeLineMax;
    }

    public String getGuaranteeLineMin() {
        return guaranteeLineMin;
    }

    public void setGuaranteeLineMin(String guaranteeLineMin) {
        this.guaranteeLineMin = guaranteeLineMin;
    }

    public String getGuaranteeDateMax() {
        return guaranteeDateMax;
    }

    public void setGuaranteeDateMax(String guaranteeDateMax) {
        this.guaranteeDateMax = guaranteeDateMax;
    }

    public String getGuaranteeDateMin() {
        return guaranteeDateMin;
    }

    public void setGuaranteeDateMin(String guaranteeDateMin) {
        this.guaranteeDateMin = guaranteeDateMin;
    }
}
