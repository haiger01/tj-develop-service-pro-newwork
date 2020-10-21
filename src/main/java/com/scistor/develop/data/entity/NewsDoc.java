package com.scistor.develop.data.entity;
//app启动

import com.scistor.develop.data.ParentEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class NewsDoc extends ParentEntity {

    public Long id;
    public String tittle;       //标题
    public String doc;          //内容
    public String url;          //图片地址
    public String docType;      //文章类型:1-政策,2-新闻,3-公告,4-成功案例,5-相关培训
    public String usercode;             //登陆用户编码
    public String companyName;          //  '企业、机构、银行名称',
    public String companyId;            //  '企业、机构、银行id',
    public String companyType;          // 类型：1企业，2金融机构，3银行，4金融局
    public String createrUser;          // '数据创建人，申请人',
    public Date createrTime;            // '数据创建时间',
    public Date updateTime;             // '数据修改时间',
    public Integer deleteFlag;          //'1:删除，0：未删除',
    public Integer watchNumber;         //热度--查看次数
    public String accessoryUrl;         //附件路径
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    public String cultivateTime;          //培训时间
    public String cultivateAccept;      //培训对象:1-企业，2-金融机构
    public String cultivateType;        //培训类型：1-股权融资，2-上市辅导，3-银行融资，4-地方金融组织培训，5-其他

    public Integer num;                 //报名人数（数据库无）

    public List<NewsApply> applyList;   //申请列表

    public Integer getWatchNumber() {
        return watchNumber;
    }

    public void setWatchNumber(Integer watchNumber) {
        this.watchNumber = watchNumber;
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

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getAccessoryUrl() {
        return accessoryUrl;
    }

    public void setAccessoryUrl(String accessoryUrl) {
        this.accessoryUrl = accessoryUrl;
    }

    public String getCultivateTime() {
        if (!StringUtils.isEmpty(cultivateTime)) {
            String[] time = cultivateTime.split("\\.");
            return time[0];
        }
        return cultivateTime;
    }

    public void setCultivateTime(String cultivateTime) {
        this.cultivateTime = cultivateTime;
    }

    public String getCultivateAccept() {
        return cultivateAccept;
    }

    public void setCultivateAccept(String cultivateAccept) {
        this.cultivateAccept = cultivateAccept;
    }

    public String getCultivateType() {
        return cultivateType;
    }

    public void setCultivateType(String cultivateType) {
        this.cultivateType = cultivateType;
    }

    public List<NewsApply> getApplyList() {
        return applyList;
    }

    public void setApplyList(List<NewsApply> applyList) {
        this.applyList = applyList;
    }
}
