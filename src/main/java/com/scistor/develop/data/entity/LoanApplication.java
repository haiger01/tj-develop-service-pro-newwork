package com.scistor.develop.data.entity;

import com.scistor.develop.data.ParentEntity;
import org.apache.ibatis.annotations.Result;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.scistor.develop.tools.SQLBlockAttribute.SQL_CK_SPLIT_STR;

public class LoanApplication extends ParentEntity {
    public String finproId;          //产品id
    public String loanType;         //申请类型 1.贷款，2担保
    public String purpose;          //用途、贷款类别
    public String applynum;         //申请金额、担保额度
    public String applydate;        //申请期限、担保期限
    public String guaranteeMode;    //担保方式
    public String acceptCompanyId;  //受理银行id、担保公司ID
    public String acceptCompanyName;//受理银行名称、担保公司名称
    public String acceptCreate;     //受理时间
    public String acceptUser;       //受理人
    public String acceptTel;        //受理人联系方式
    public String acceptMesage;     //受理意见
    public String checkStart;       //数据查询开始时间
    public String checkEnd;         //数据查询结束时间
    public String checkType;        //审核状态：0:未申请,1企业申请，2同意授权 3.银行受理中 4.初审不通过，4.1现场调查不通过4.2资料收集不通过4.3银行开户不通过，，5.初审通过,5.1现场调查通过,
    //         5.2资料收集通过,5.3银行开户通过，5.4上报 6.审核通过待授信，7.审核不通过 7.5 已授信，8：已放款，9：拒绝放款
    public String checkMesage;      //审核备注
    public String loanNum;          //贷款金额
    public Date loanTime;           //下款时间
    public String loanAccount;      //下款账号、
    public String colAccount;       //收款认证账户
    public String colName;          //收款认证户名
    public String colBank;          //收款认证银行
    public String guaDate;          //融资担保期限
    public String proName;         //产品名称
    public String guaNum;           //融资担保数量
    public String guaRate;          //融资担保利率
    public String guaRegion;        //融资担保适用区市
    public String guaGuaranteeMode; //融资担保适用方式
    public String lineofcredit;     //信用额度
    public String rateofcredit;     //授信利率
    public String dateofcredit;     //授信期限

    public String cooperationBank;  //合作银行
    public String companyDeleteFlag;//企业删除标志位，1：删除，0：不删除
    public String guarantorsDeleteFlag;//担保机构或者银行删除标志位，1：删除，0：不删除

    public String investigationMsg;//现场调查意见
    public String collectionMsg;   //资料收集意见
    public String bankopenMsg;     //银行开户意见
    public String beforCheckType; //上一个审核状态

    public Date investigationTime; //现场调查时间
    public Date collectionTime;    //资料收集时间
    public Date bankopenTime;      //银行开户时间
    public Date creditTime;        //授信时间
    public Date reportTime;        //上报时间
    public Object records;         //痕迹记录

    public Long appid;              //需求ID
    public Long id;
    public String usercode;             //登陆用户编码
    public String companyName;          //  '企业、机构、银行名称',
    public String companyId;            //  '企业、机构、银行id',
    public String companyType;          // '类型：1企业，2金融机构，3银行，4金融局',
    public String createrUser;          // '数据创建人，申请人',
    public Date createrTime;            // '数据创建时间',
    public Date updateTime;             // '数据修改时间',
    public Integer deleteFlag;          //'1:删除，0：未删除',

    public String keyCode;
    public String dataLabelTypes;

    public String guarantyStyleEnd;     //最终担保方式

    public String getGuarantyStyleEnd() {
        return guarantyStyleEnd;
    }

    public void setGuarantyStyleEnd(String guarantyStyleEnd) {
        this.guarantyStyleEnd = guarantyStyleEnd;
    }

    public String getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    public String getDataLabelTypes() {
        return dataLabelTypes;
    }

    public void setDataLabelTypes(String dataLabelTypes) {
        this.dataLabelTypes = dataLabelTypes;
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

    public String getCooperationBank() {
        return cooperationBank;
    }

    public void setCooperationBank(String cooperationBank) {
        this.cooperationBank = cooperationBank;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public void setRecords(Object records) {
        this.records = records;
    }

    public Date getCreditTime() {
        return creditTime;
    }

    public void setCreditTime(Date creditTime) {
        this.creditTime = creditTime;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

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

    public String getBeforCheckType() {
        return beforCheckType;
    }

    public void setBeforCheckType(String beforCheckType) {
        this.beforCheckType = beforCheckType;
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

    public String getCompanyDeleteFlag() {
        return companyDeleteFlag;
    }

    public void setCompanyDeleteFlag(String companyDeleteFlag) {
        this.companyDeleteFlag = companyDeleteFlag;
    }

    public String getGuarantorsDeleteFlag() {
        return guarantorsDeleteFlag;
    }

    public void setGuarantorsDeleteFlag(String guarantorsDeleteFlag) {
        this.guarantorsDeleteFlag = guarantorsDeleteFlag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAppid() {
        return appid;
    }

    public void setAppid(Long appid) {
        this.appid = appid;
    }

    public String getLineofcredit() {
        return lineofcredit;
    }

    public void setLineofcredit(String lineofcredit) {
        this.lineofcredit = lineofcredit;
    }

    public String getGuaRegion() {
        return guaRegion;
    }

    public void setGuaRegion(String guaRegion) {
        this.guaRegion = guaRegion;
    }

    public String getGuaGuaranteeMode() {
        return guaGuaranteeMode;
    }

    public void setGuaGuaranteeMode(String guaGuaranteeMode) {
        this.guaGuaranteeMode = guaGuaranteeMode;
    }

    public String getApplynum() {
        return applynum;
    }

    public String getGuaDate() {
        return guaDate;
    }

    public void setGuaDate(String guaDate) {
        this.guaDate = guaDate;
    }

    public String getGuaNum() {
        return guaNum;
    }

    public void setGuaNum(String guaNum) {
        this.guaNum = guaNum;
    }

    public String getGuaRate() {
        return guaRate;
    }

    public void setGuaRate(String guaRate) {
        this.guaRate = guaRate;
    }

    public void setApplynum(String applynum) {
        this.applynum = applynum;
    }

    public String getApplydate() {
        return applydate;
    }

    public void setApplydate(String applydate) {
        this.applydate = applydate;
    }

    public String getGuaranteeMode() {
        return guaranteeMode;
    }

    public String getCheckMesage() {
        return checkMesage;
    }

    public void setCheckMesage(String checkMesage) {
        this.checkMesage = checkMesage;
    }

    public String getColAccount() {
        return colAccount;
    }

    public void setColAccount(String colAccount) {
        this.colAccount = colAccount;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getColBank() {
        return colBank;
    }

    public void setColBank(String colBank) {
        this.colBank = colBank;
    }

    public void setGuaranteeMode(String guaranteeMode) {
        this.guaranteeMode = guaranteeMode;
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

    public String getFinproId() {
        return finproId;
    }

    public void setFinproId(String finproId) {
        this.finproId = finproId;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
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

    public String getLoanNum() {
        return loanNum;
    }

    public void setLoanNum(String loanNum) {
        this.loanNum = loanNum;
    }

    public Date getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(Date loanTime) {
        this.loanTime = loanTime;
    }

    public String getLoanAccount() {
        return loanAccount;
    }

    public void setLoanAccount(String loanAccount) {
        this.loanAccount = loanAccount;
    }
}
