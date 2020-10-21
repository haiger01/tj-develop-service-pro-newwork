package com.scistor.develop.data.entity;

import com.scistor.develop.data.ParentEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.scistor.develop.tools.SQLBlockAttribute.SQL_CK_SPLIT_STR;

public class CompanyInfo extends ParentEntity {

    public String orgNumber="";        //组织机构代码
    public String creditCode;       //统一社会信用代码
    public String icpCode;          //ICP备案号
    public String regAddPro;        //省份code
    public String regAddCity;       //城市code
    public String regAddRegion;     //区域code
    public String regAddInfo;       //注册地址
    public String businessAddress;  //实际经营地址
    public String authorizeUrl;     //授权书
    public String authorizeer;      //授权人
    public String buslicense;       //工商营业执照
    public String legPersonPic;     //法人手持身份证照片
    public String legPerIdNum;      //法人身份证号
    public String comAccount;       //开户行账号
    public String accountName;      //户名
    public String accountBank;      //开户行名称
    public String bankAddPro;       //省份code
    public String bankAddCity;      //城市code
    public String bankAddRegion;    //区域code
    public String bankAddAddress;   //注册地址
    public String adminName;        //管理员身份证姓名
    public String adminId;          //管理员身份证号码
    public String adminTel;         //管理员手机号
    public String otherName;        //其他联系人
    public String otherTel;         //其他联系人的联系方式
    public String colAccount;       //汇款认证账户
    public String colName;          //汇款认证户名
    public String colBank;          //汇款认证银行
    public String colNum;           //汇款认证金额
    public String verificavtionDate;//最后打款日期
    public String finOperate;       //认证状态：0,等待打款，1认证中，2认证通过，3认证失败
    public String mainBank;         //主要结算银行
    public String mainBusiness;     //主营业务
    public Long id;
    public String usercode;             //登陆用户编码
    public String companyName;          //  '企业、机构、银行名称',
    public String companyId;            //  '企业、机构、银行id',
    public String companyType;          // '类型：1企业，2金融机构，3银行，4金融局',
    public String createrUser;          // '数据创建人，申请人',
    public Date createrTime;            // '数据创建时间',
    public Date updateTime;             // '数据修改时间',
    public Integer deleteFlag;          //'1:删除，0：未删除',
    public String customers;          //上下游客户
    public String loansSituation;     //在其他渠道贷款情况
    public String financialStatements;//在其他渠道贷款情况
    public String annualTaxReturns;    //年度纳税申报情况文件地址
    public String monthlyTaxReturns;  //月纳税申报情况文件地址
    public String capitalFlow;        //主要结算账户资金流动情况
    public Integer refundStatus;      //金融局退款时间，默认未0(不传的时候默认为0)
    public Date refundTime;           //金融局确认退款时间---返回给企业之前的打款金额
    public Date playTime;             //金融局确认打款时间---即金融局确认企业实名的时间
    public Integer playStatus;         //金融局确认打款状态---点击收到修改状态

    public String playCode;             //金融局确认打款编号
    public String refundCode;             //金融局确认首款编号


    public String acceptanceBorrower; //验收借方
    public String acceptanceLender; //验收贷方

    public String acceptanceRefundBorrower; //验收退款贷方
    public String acceptanceRefundLender; //验收退款贷方

    public String addressDetails;   //地址详情

    public String businessPlan;//商业计划书

    public String roadshowVideo;//路演视屏

    public String investmentIndustry;//发展行业

    public String investmentStage;//发展阶段

    public String financingAmount;//融资金额

    public String dataCollectionUrl;//数据收集

    public String getDataCollectionUrl() {
        return dataCollectionUrl;
    }

    public void setDataCollectionUrl(String dataCollectionUrl) {
        this.dataCollectionUrl = dataCollectionUrl;
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

    public String getFinancingAmount() {
        return financingAmount;
    }

    public void setFinancingAmount(String financingAmount) {
        this.financingAmount = financingAmount;
    }

    public String getBusinessPlan() {
        return businessPlan;
    }

    public void setBusinessPlan(String businessPlan) {
        this.businessPlan = businessPlan;
    }

    public String getRoadshowVideo() {
        return roadshowVideo;
    }

    public void setRoadshowVideo(String roadshowVideo) {
        this.roadshowVideo = roadshowVideo;
    }

    public String getPlayCode() {
        return playCode;
    }

    public void setPlayCode(String playCode) {
        this.playCode = playCode;
    }

    public String getRefundCode() {
        return refundCode;
    }

    public void setRefundCode(String refundCode) {
        this.refundCode = refundCode;
    }

    public String getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails;
    }

    public String getAcceptanceBorrower() {
        if(StringUtils.isEmpty(this.acceptanceBorrower))
            this.acceptanceBorrower = this.getColNum();
        return acceptanceBorrower;
    }

    public void setAcceptanceBorrower(String acceptanceBorrower) {
        this.acceptanceBorrower = acceptanceBorrower;
    }

    public String getAcceptanceLender() {
        if(StringUtils.isEmpty(this.acceptanceLender))
            this.acceptanceLender = this.getColNum();
        return acceptanceLender;
    }

    public void setAcceptanceLender(String acceptanceLender) {
        this.acceptanceLender = acceptanceLender;
    }

    public String getAcceptanceRefundBorrower() {
        if(StringUtils.isEmpty(this.acceptanceRefundBorrower))
            this.acceptanceRefundBorrower = this.getColNum();
        return acceptanceRefundBorrower;
    }

    public void setAcceptanceRefundBorrower(String acceptanceRefundBorrower) {
        this.acceptanceRefundBorrower = acceptanceRefundBorrower;
    }

    public String getAcceptanceRefundLender() {
        if(StringUtils.isEmpty(this.acceptanceRefundLender))
            this.acceptanceRefundLender = this.getColNum();
        return acceptanceRefundLender;
    }

    public void setAcceptanceRefundLender(String acceptanceRefundLender) {
        this.acceptanceRefundLender = acceptanceRefundLender;
    }

    public Integer getPlayStatus() {
        return playStatus;
    }

    public void setPlayStatus(Integer playStatus) {
        this.playStatus = playStatus;
    }

    public Integer getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public Date getPlayTime() {
        return playTime;
    }

    public void setPlayTime(Date playTime) {
        this.playTime = playTime;
    }

    public String getFinancialStatements() {
        return financialStatements;
    }

    public void setFinancialStatements(String financialStatements) {
        this.financialStatements = financialStatements;
    }

    public String getAnnualTaxReturns() {
        return annualTaxReturns;
    }

    public void setAnnualTaxReturns(String annualTaxReturns) {
        this.annualTaxReturns = annualTaxReturns;
    }

    public String getMonthlyTaxReturns() {
        return monthlyTaxReturns;
    }

    public String getCustomers() {
        return customers;
    }

    public void setCustomers(String customers) {
        this.customers = customers;
    }

    public String getLoansSituation() {
        return loansSituation;
    }

    public void setLoansSituation(String loansSituation) {
        this.loansSituation = loansSituation;
    }

    public void setMonthlyTaxReturns(String monthlyTaxReturns) {
        this.monthlyTaxReturns = monthlyTaxReturns;
    }

    public String getCapitalFlow() {
        return capitalFlow;
    }

    public void setCapitalFlow(String capitalFlow) {
        this.capitalFlow = capitalFlow;
    }

    public String getMainBusiness() {
        return mainBusiness;
    }

    public void setMainBusiness(String mainBusiness) {
        this.mainBusiness = mainBusiness;
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

    public String getBusinessAddress() {
        return businessAddress;
    }

    public String getMainBank() {
        return mainBank;
    }

    public void setMainBank(String mainBank) {
        this.mainBank = mainBank;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getAuthorizeUrl() {
        return authorizeUrl;
    }

    public void setAuthorizeUrl(String authorizeUrl) {
        this.authorizeUrl = authorizeUrl;
    }

    public String getAuthorizeer() {
        return authorizeer;
    }

    public void setAuthorizeer(String authorizeer) {
        this.authorizeer = authorizeer;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getOtherTel() {
        return otherTel;
    }

    public void setOtherTel(String otherTel) {
        this.otherTel = otherTel;
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

    public String getOrgNumber() {
        return orgNumber;
    }

    public void setOrgNumber(String orgNumber) {
        this.orgNumber = orgNumber;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getIcpCode() {
        return icpCode;
    }

    public void setIcpCode(String icpCode) {
        this.icpCode = icpCode;
    }

    public String getRegAddPro() {
        return regAddPro;
    }

    public void setRegAddPro(String regAddPro) {
        this.regAddPro = regAddPro;
    }

    public String getRegAddCity() {
        return regAddCity;
    }

    public void setRegAddCity(String regAddCity) {
        this.regAddCity = regAddCity;
    }

    public String getRegAddRegion() {
        return regAddRegion;
    }

    public void setRegAddRegion(String regAddRegion) {
        this.regAddRegion = regAddRegion;
    }

    public String getRegAddInfo() {
        return regAddInfo;
    }

    public void setRegAddInfo(String regAddInfo) {
        this.regAddInfo = regAddInfo;
    }

    public String getBuslicense() {
        return buslicense;
    }

    public void setBuslicense(String buslicense) {
        this.buslicense = buslicense;
    }

    public String getLegPersonPic() {
        return legPersonPic;
    }

    public void setLegPersonPic(String legPersonPic) {
        this.legPersonPic = legPersonPic;
    }

    public String getLegPerIdNum() {
        return legPerIdNum;
    }

    public void setLegPerIdNum(String legPerIdNum) {
        this.legPerIdNum = legPerIdNum;
    }

    public String getComAccount() {
        return comAccount;
    }

    public void setComAccount(String comAccount) {
        this.comAccount = comAccount;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountBank() {
        return accountBank;
    }

    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank;
    }

    public String getBankAddPro() {
        return bankAddPro;
    }

    public void setBankAddPro(String bankAddPro) {
        this.bankAddPro = bankAddPro;
    }

    public String getBankAddCity() {
        return bankAddCity;
    }

    public void setBankAddCity(String bankAddCity) {
        this.bankAddCity = bankAddCity;
    }

    public String getBankAddRegion() {
        return bankAddRegion;
    }

    public void setBankAddRegion(String bankAddRegion) {
        this.bankAddRegion = bankAddRegion;
    }

    public String getBankAddAddress() {
        return bankAddAddress;
    }

    public void setBankAddAddress(String bankAddAddress) {
        this.bankAddAddress = bankAddAddress;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminTel() {
        return adminTel;
    }

    public void setAdminTel(String adminTel) {
        this.adminTel = adminTel;
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

    public String getColNum() {
        return colNum;
    }

    public void setColNum(String colNum) {
        this.colNum = colNum;
    }

    public String getVerificavtionDate() {
        return verificavtionDate;
    }

    public void setVerificavtionDate(String verificavtionDate) {
        this.verificavtionDate = verificavtionDate;
    }

    public String getFinOperate() {
        return finOperate;
    }

    public void setFinOperate(String finOperate) {
        this.finOperate = finOperate;
    }
}
