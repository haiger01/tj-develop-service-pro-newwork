package com.scistor.develop.data.entity;

import com.scistor.develop.data.ParentEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.scistor.develop.tools.SQLBlockAttribute.SQL_CK_SPLIT_STR;

/**
 * 股权审核表
 */
public class EquityAudit extends ParentEntity {
    public Long id;
    public String companyName;
    public String companyId;
    public String createrTime;
    public Date updateTime;
    public String deleteFlag;
    public String equityId;
    public String acceptCompanyId;
    public String acceptCompanyName;
    public String acceptCreate;
    public String acceptUser;
    public String acceptTel;
    public String acceptMesage;
    public String checkStart;
    public String checkEnd;
    public String checkType;
    public Date loanTime;
    public String usercode;
    public String applynum;
    public String checkMesage;
    public String investigationMsg;
    public Date investigationTime;
    public String collectionMsg;
    public Date collectionTime;
    public String bankopenMsg;
    public Date bankopenTime;
    public String beforCheckType;
    public String records;
    public Date reportTime;
    public Date creditTime;
    public String equityName;

    public String rateofcredit;//授信利率
    public String dateofcredit;//授信期限
    public String lineofcredit;//审核通过后的授信金额
    public String loanNum;//贷款金额(实际放款金额)

    public String getRateofcredit() {
        return rateofcredit;
    }

    public void setRateofcredit(String rateofcredit) {
        this.rateofcredit = rateofcredit;
    }

    public String getDateofcredit() {
        return dateofcredit;
    }

    public void setDateofcredit(String dateofcredit) {
        this.dateofcredit = dateofcredit;
    }

    public String getLineofcredit() {
        return lineofcredit;
    }

    public void setLineofcredit(String lineofcredit) {
        this.lineofcredit = lineofcredit;
    }

    public String getLoanNum() {
        return loanNum;
    }

    public void setLoanNum(String loanNum) {
        this.loanNum = loanNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCreaterTime() {
        return createrTime;
    }

    public void setCreaterTime(String createrTime) {
        this.createrTime = createrTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date update_time) {
        this.updateTime = update_time;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getEquityId() {
        return equityId;
    }

    public void setEquityId(String equityId) {
        this.equityId = equityId;
    }

    public String getAcceptCompanyId() {
        return acceptCompanyId;
    }

    public void setAcceptCompanyId(String acceptCompanyId) {
        this.acceptCompanyId = acceptCompanyId;
    }

    public String getAcceptCompanyName() {
        return acceptCompanyName;
    }

    public void setAcceptCompanyName(String acceptCompanyName) {
        this.acceptCompanyName = acceptCompanyName;
    }

    public String getAcceptCreate() {
        return acceptCreate;
    }

    public void setAcceptCreate(String acceptCreate) {
        this.acceptCreate = acceptCreate;
    }

    public String getAcceptUser() {
        return acceptUser;
    }

    public void setAcceptUser(String acceptUser) {
        this.acceptUser = acceptUser;
    }

    public String getAcceptTel() {
        return acceptTel;
    }

    public void setAcceptTel(String acceptTel) {
        this.acceptTel = acceptTel;
    }

    public String getAcceptMesage() {
        return acceptMesage;
    }

    public void setAcceptMesage(String acceptMesage) {
        this.acceptMesage = acceptMesage;
    }

    public String getCheckStart() {
        return checkStart;
    }

    public void setCheckStart(String checkStart) {
        this.checkStart = checkStart;
    }

    public String getCheckEnd() {
        return checkEnd;
    }

    public void setCheckEnd(String checkEnd) {
        this.checkEnd = checkEnd;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public Date getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(Date loanTime) {
        this.loanTime = loanTime;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getApplynum() {
        return applynum;
    }

    public void setApplynum(String applynum) {
        this.applynum = applynum;
    }

    public String getCheckMesage() {
        return checkMesage;
    }

    public void setCheckMesage(String checkMesage) {
        this.checkMesage = checkMesage;
    }

    public String getInvestigationMsg() {
        return investigationMsg;
    }

    public void setInvestigationMsg(String investigationMsg) {
        this.investigationMsg = investigationMsg;
    }

    public Date getInvestigationTime() {
        return investigationTime;
    }

    public void setInvestigationTime(Date investigationTime) {
        this.investigationTime = investigationTime;
    }

    public String getCollectionMsg() {
        return collectionMsg;
    }

    public void setCollectionMsg(String collectionMsg) {
        this.collectionMsg = collectionMsg;
    }

    public Date getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(Date collectionTime) {
        this.collectionTime = collectionTime;
    }

    public String getBankopenMsg() {
        return bankopenMsg;
    }

    public void setBankopenMsg(String bankopenMsg) {
        this.bankopenMsg = bankopenMsg;
    }

    public Date getBankopenTime() {
        return bankopenTime;
    }

    public void setBankopenTime(Date bankopenTime) {
        this.bankopenTime = bankopenTime;
    }

    public String getBeforCheckType() {
        return beforCheckType;
    }

    public void setBeforCheckType(String beforCheckType) {
        this.beforCheckType = beforCheckType;
    }

    public Object getRecords() {
        if (records != null) {
            Map map = new HashMap();
            for (String s : records.toString().split(",")) {
                map.put(s.split(SQL_CK_SPLIT_STR)[0], s.split(SQL_CK_SPLIT_STR)[1]);
            }
            return map;
        }
        return records;
    }

    public Object getRecords_YH_LSNOT_STR() {
        return records;
    }

    public void setRecords(String records) {
        this.records = records;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public Date getCreditTime() {
        return creditTime;
    }

    public void setCreditTime(Date creditTime) {
        this.creditTime = creditTime;
    }

    public String getEquityName() {
        return equityName;
    }

    public void setEquityName(String equityName) {
        this.equityName = equityName;
    }
}
