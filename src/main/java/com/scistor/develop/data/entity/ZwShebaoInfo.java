package com.scistor.develop.data.entity;

import java.util.Date;

/**
 * 社保信息表
 * 表：shebao_info
 */
public class ZwShebaoInfo {
    public String id;

    public String marSubName;           //市场主体名称

    public String buslicRegNumber;      //营业执照注册号

    public String orgCode;              //组织机构代码

    public String socInsRegCerDate;       //社会保险登记证发证日期

    public String socInsRegCerCode;     //社会保险登记证编码

    public String penPayStatus;        //养老缴费状态:1参保2暂停3终止

    public String penPayres;           //养老缴费人数

    public String penPayBase;           //养老缴费基数

    public String penPayRatio;         //养老缴费比例

    public String unePayStatus;        //失业缴费状态:1参保2暂停3终止

    public String unePayers;       //失业缴费人数

    public String unePayBase;           //失业缴费基数

    public String unePayRatio;          //失业缴费比例

    public String medPayStatus;        //医疗缴费状态:1参保2暂停3终止

    public String medPayers;           //医疗缴费人数

    public String medPayBase;           //医疗缴费基数

    public String medPayRatio;          //医疗缴费比例

    public String workInjPayStatus;    //工伤缴费状态:1参保2暂停3终止

    public String workInjPayers;       //工伤缴费人数

    public String workInjPayBase;       //工伤缴费基数

    public String workInjPayRatio;      //工伤缴费比例

    public String matPayStatus;        //生育缴费状态:1参保2暂停3终止

    public String matPayers;           //生育缴费人数

    public String matPayBase;           //生育缴费基数

    public String matPayRatio;          //生育缴费比例

    public String entParNumCurrent;     //企业参保人数（在职）

    public String entParNumRetire;      //企业参保人数（退休）

    public String entParNumRetHonor;    //企业参保人数（离休）

    public String entPayPenAmount;      //企业实际缴费金额(养老）

    public String entPayUneAmount;      //企业实际缴费金额(失业）

    public String entMonUnpPenAmount;   //企业当月应缴未缴金额（养老）

    public String entPayInjAmount;      //企业实际缴费金额(工伤）

    public String entMonUnpUneAmount;   //企业当月应缴未缴金额（失业）

    public String entMonUnpInjAmount;   //企业当月应缴未缴金额（工伤）

    public String medInsPay;            //企业实际缴费金额（医疗）

    public String medInsArr;            //企业当月应缴未缴金额（医疗）

    public String birInsPay;            //企业实际缴费金额（生育）

    public String birInsArr;            //企业当月应缴未缴金额（生育）

    public String dataStatus;           //数据标识位状态

    public String lastModTime;          //业务中最后修改时间

    public String period;               //结算期

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarSubName() {
        return marSubName;
    }

    public void setMarSubName(String marSubName) {
        this.marSubName = marSubName;
    }

    public String getBuslicRegNumber() {
        return buslicRegNumber;
    }

    public void setBuslicRegNumber(String buslicRegNumber) {
        this.buslicRegNumber = buslicRegNumber;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getSocInsRegCerDate() {
        return socInsRegCerDate;
    }

    public void setSocInsRegCerDate(String socInsRegCerDate) {
        this.socInsRegCerDate = socInsRegCerDate;
    }

    public String getSocInsRegCerCode() {
        return socInsRegCerCode;
    }

    public void setSocInsRegCerCode(String socInsRegCerCode) {
        this.socInsRegCerCode = socInsRegCerCode;
    }

    public String getPenPayStatus() {
        return penPayStatus;
    }

    public void setPenPayStatus(String penPayStatus) {
        this.penPayStatus = penPayStatus;
    }

    public String getPenPayres() {
        return penPayres;
    }

    public void setPenPayres(String penPayres) {
        this.penPayres = penPayres;
    }

    public String getPenPayBase() {
        return penPayBase;
    }

    public void setPenPayBase(String penPayBase) {
        this.penPayBase = penPayBase;
    }

    public String getPenPayRatio() {
        return penPayRatio;
    }

    public void setPenPayRatio(String penPayRatio) {
        this.penPayRatio = penPayRatio;
    }

    public String getUnePayStatus() {
        return unePayStatus;
    }

    public void setUnePayStatus(String unePayStatus) {
        this.unePayStatus = unePayStatus;
    }

    public String getUnePayers() {
        return unePayers;
    }

    public void setUnePayers(String unePayers) {
        this.unePayers = unePayers;
    }

    public String getUnePayBase() {
        return unePayBase;
    }

    public void setUnePayBase(String unePayBase) {
        this.unePayBase = unePayBase;
    }

    public String getUnePayRatio() {
        return unePayRatio;
    }

    public void setUnePayRatio(String unePayRatio) {
        this.unePayRatio = unePayRatio;
    }

    public String getMedPayStatus() {
        return medPayStatus;
    }

    public void setMedPayStatus(String medPayStatus) {
        this.medPayStatus = medPayStatus;
    }

    public String getMedPayers() {
        return medPayers;
    }

    public void setMedPayers(String medPayers) {
        this.medPayers = medPayers;
    }

    public String getMedPayBase() {
        return medPayBase;
    }

    public void setMedPayBase(String medPayBase) {
        this.medPayBase = medPayBase;
    }

    public String getMedPayRatio() {
        return medPayRatio;
    }

    public void setMedPayRatio(String medPayRatio) {
        this.medPayRatio = medPayRatio;
    }

    public String getWorkInjPayStatus() {
        return workInjPayStatus;
    }

    public void setWorkInjPayStatus(String workInjPayStatus) {
        this.workInjPayStatus = workInjPayStatus;
    }

    public String getWorkInjPayers() {
        return workInjPayers;
    }

    public void setWorkInjPayers(String workInjPayers) {
        this.workInjPayers = workInjPayers;
    }

    public String getWorkInjPayBase() {
        return workInjPayBase;
    }

    public void setWorkInjPayBase(String workInjPayBase) {
        this.workInjPayBase = workInjPayBase;
    }

    public String getWorkInjPayRatio() {
        return workInjPayRatio;
    }

    public void setWorkInjPayRatio(String workInjPayRatio) {
        this.workInjPayRatio = workInjPayRatio;
    }

    public String getMatPayStatus() {
        return matPayStatus;
    }

    public void setMatPayStatus(String matPayStatus) {
        this.matPayStatus = matPayStatus;
    }

    public String getMatPayers() {
        return matPayers;
    }

    public void setMatPayers(String matPayers) {
        this.matPayers = matPayers;
    }

    public String getMatPayBase() {
        return matPayBase;
    }

    public void setMatPayBase(String matPayBase) {
        this.matPayBase = matPayBase;
    }

    public String getMatPayRatio() {
        return matPayRatio;
    }

    public void setMatPayRatio(String matPayRatio) {
        this.matPayRatio = matPayRatio;
    }

    public String getEntParNumCurrent() {
        return entParNumCurrent;
    }

    public void setEntParNumCurrent(String entParNumCurrent) {
        this.entParNumCurrent = entParNumCurrent;
    }

    public String getEntParNumRetire() {
        return entParNumRetire;
    }

    public void setEntParNumRetire(String entParNumRetire) {
        this.entParNumRetire = entParNumRetire;
    }

    public String getEntParNumRetHonor() {
        return entParNumRetHonor;
    }

    public void setEntParNumRetHonor(String entParNumRetHonor) {
        this.entParNumRetHonor = entParNumRetHonor;
    }

    public String getEntPayPenAmount() {
        return entPayPenAmount;
    }

    public void setEntPayPenAmount(String entPayPenAmount) {
        this.entPayPenAmount = entPayPenAmount;
    }

    public String getEntPayUneAmount() {
        return entPayUneAmount;
    }

    public void setEntPayUneAmount(String entPayUneAmount) {
        this.entPayUneAmount = entPayUneAmount;
    }

    public String getEntMonUnpPenAmount() {
        return entMonUnpPenAmount;
    }

    public void setEntMonUnpPenAmount(String entMonUnpPenAmount) {
        this.entMonUnpPenAmount = entMonUnpPenAmount;
    }

    public String getEntPayInjAmount() {
        return entPayInjAmount;
    }

    public void setEntPayInjAmount(String entPayInjAmount) {
        this.entPayInjAmount = entPayInjAmount;
    }

    public String getEntMonUnpUneAmount() {
        return entMonUnpUneAmount;
    }

    public void setEntMonUnpUneAmount(String entMonUnpUneAmount) {
        this.entMonUnpUneAmount = entMonUnpUneAmount;
    }

    public String getEntMonUnpInjAmount() {
        return entMonUnpInjAmount;
    }

    public void setEntMonUnpInjAmount(String entMonUnpInjAmount) {
        this.entMonUnpInjAmount = entMonUnpInjAmount;
    }

    public String getMedInsPay() {
        return medInsPay;
    }

    public void setMedInsPay(String medInsPay) {
        this.medInsPay = medInsPay;
    }

    public String getMedInsArr() {
        return medInsArr;
    }

    public void setMedInsArr(String medInsArr) {
        this.medInsArr = medInsArr;
    }

    public String getBirInsPay() {
        return birInsPay;
    }

    public void setBirInsPay(String birInsPay) {
        this.birInsPay = birInsPay;
    }

    public String getBirInsArr() {
        return birInsArr;
    }

    public void setBirInsArr(String birInsArr) {
        this.birInsArr = birInsArr;
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getLastModTime() {
        return lastModTime;
    }

    public void setLastModTime(String lastModTime) {
        this.lastModTime = lastModTime;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}