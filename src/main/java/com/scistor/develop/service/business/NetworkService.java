package com.scistor.develop.service.business;

import com.scistor.develop.dao.mapper.BaseDao;
import com.scistor.develop.data.entity.*;
import com.scistor.develop.service.ParentService;
import com.scistor.develop.util.DateTool;
import com.scistor.develop.util.PDFReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

import static com.scistor.develop.tools.BlockAttribute.GENERATE_THE_DATA_MAP;
import static com.scistor.develop.util.SqlUtil.map2where;

@Service
public class NetworkService extends ParentService {

    static final String theTableName = "net_work";

    @Value("${filepath.file_path}")
    private String filePath;
    @Value("${filepath.file_img_path}")
    private String imgPath;

    @Autowired
    BaseDao baseDao;

    public Map generateTheDataDown(Network network) {
        String whereSql = map2where(network, "auditId", "demandId","codeKey");
        //查询当前天最新的数据
        return baseDao.mapBySqlModel("select * from net_work where date(create_time) = curdate() and " + whereSql +" order by create_time desc limit 1");
    }

    public void addGenerateTheData(Network network) {
        network.setCreateTime(new Date());
        network.setUpdateTime(new Date());
        parentRecordInsert(theTableName, network);
    }


    /**
     * 获取报表数据
     * @return
     */
    public String getGenerateTheDataList(ZwCompany zwCompany) {
        Map<String,Object> totalMap = new HashMap<String,Object>();

        /**
         * ！！！！必要条件！！！！
         *
         * 企业名称，信用代码，组织机构代码，用水户号
         *
         */

        String entId = "";//企业ID            ：BF7BC2F0B89941369E5D083DCD1A0F68
        String entName = zwCompany.getCompanyName();//企业名称
        String orgNo = zwCompany.getOrgNo();//企业实名认证时填报的统一社会信用机构代码 credit_code(发展的)，统一社会信用代码：1202251003611
        String orgCode = zwCompany.getOrgCode();//组织机构代码//org_number(发展的)
        String waterUserNo = zwCompany.getWaterNumber();//水表注册号

        //传过来的
        //  company zwCompany                                                                           一、企业基本信息(企业填报)
        /**
         *
         * 企业信息直接封装---也可以根据企业名称查询一遍
         * 返回数据：map
         */

        Date date = new Date();
        //添加报告编号
        zwCompany.setReportNumber(DateTool.getDateTime2StrByData(date,DateTool.SHORT_DATE_FORMAT_DAY));
        String day = DateTool.date2Str(date);
        zwCompany.setReportDate(day);
        totalMap.put("company",zwCompany);

        /*if(zwCompany != null){
            PDFReport pdfReport2 = new PDFReport();
            return pdfReport2.saveExportPDF(totalMap,"D:\\work\\test\\"+"天津市地方企业评价报告_"+zwCompany.getCheckName()+"_"+day+".pdf",imgPath);
        }*/

        /**
         * 对应实体类名称：ZwScjgwEntinfo
         *
         * 功能模块：二、工商信息
         *
         * psf总数据里面的key：business
         *
         * 查询数据条件
         *          entName：企业名称
         *          orgNo：企业实名认证时填报的统一社会信用机构代码(取ZwCompany里面的orgNo字段)
         * 返回数据：map
         */
        Map businessMap = null;
        try{
            businessMap = baseDao.mapBySqlModel(GENERATE_THE_DATA_MAP.get("business")
                    + " WHERE `scjgw_data`.scjgw_entinfo.entName = '" + entName
                    + "' OR `scjgw_data`.scjgw_entinfo.creditCode = '" + orgNo + "'");
        } catch (Exception e) {
            System.out.println("二、工商信息----加载数据异常:" + e.getMessage());
        }
        if(CollectionUtils.isEmpty(businessMap)){
            businessMap = new HashMap();
        } else {
            entId = businessMap.get("entId")+"";
        }
        totalMap.put("business",businessMap);


        /**
         * 对应实体类名称：ZwScjgwPersoninfo
         *
         * 功能模块：三、市场监管委-(一)企业人员信息
         *
         * psf总数据里面的key：comPerson
         *
         * 查询数据条件
         *          entName：企业名称
         *          orgNo：企业实名认证时填报的统一社会信用机构代码
         * 返回数据：list
         */
        List<Map<String,Object>> comPersonList = null;
        try{
            comPersonList = baseDao.mapBySql(GENERATE_THE_DATA_MAP.get("comPerson")
                    + " WHERE `scjgw_data`.scjgw_personinfo.entId IN(SELECT `scjgw_data`.scjgw_entinfo.entId"
                    + " FROM `scjgw_data`.scjgw_entinfo WHERE `scjgw_data`.scjgw_entinfo.entName = '"
                    + entName +"' OR `scjgw_data`.scjgw_entinfo.creditCode = '"+ orgNo +"')");
        } catch (Exception e) {
            System.out.println("三、市场监管委-(一)企业人员信息----加载数据异常:" + e.getMessage());
        }
        if(CollectionUtils.isEmpty(comPersonList)){
            comPersonList = new ArrayList<>();
        }
        totalMap.put("comPerson",comPersonList);



        /**
         * 对应实体类名称：ZwScjgwGdczinfo
         *
         * 功能模块：三、市场监管委-(二)股东信息-1）股东出资记录
         *
         * psf总数据里面的key：comShareholderContribute
         *
         * 查询数据条件
         *          entId：企业ID(政务网区ID)
         * 返回数据：list
         */
        List<Map<String,Object>> comShareholderContributeList = null;
        try{
            comShareholderContributeList = baseDao.mapBySql(GENERATE_THE_DATA_MAP.get("comShareholderContribute")
                    + " WHERE `scjgw_data`.scjgw_gdczinfo.entId = '" + entId + "'");
        } catch (Exception e) {
            System.out.println("三、市场监管委-(二)股东信息-1）股东出资记录----加载数据异常:" + e.getMessage());
        }
        if(CollectionUtils.isEmpty(comShareholderContributeList)){
            comShareholderContributeList = new ArrayList<>();
        }
        totalMap.put("comShareholderContribute",comShareholderContributeList);



        /**
         * 对应实体类名称：ZwScjgwGdbginfo
         *
         * 功能模块：三、市场监管委-(二)股东信息-2）股东变更信息
         *
         * psf总数据里面的key：comShareholder
         *
         * 查询数据条件
         *          entId：企业ID(政务网区ID)
         * 返回数据：list
         */
        List<Map<String,Object>> comShareholderList = null;
        try{
            comShareholderList = baseDao.mapBySql(GENERATE_THE_DATA_MAP.get("comShareholder")
                    + " WHERE `scjgw_data`.scjgw_gdczinfo.entId = '" + entId + "'");
        } catch (Exception e) {
            System.out.println("三、市场监管委-(二)股东信息-2）股东变更信息----加载数据异常:" + e.getMessage());
        }
        if(CollectionUtils.isEmpty(comShareholderList)){
            comShareholderList = new ArrayList<>();
        }
        totalMap.put("comShareholder",comShareholderList);


        /**
         * 对应实体类名称：ZwScjgwNbinfo
         *
         * 功能模块：三、市场监管委-(三)企业年报
         *
         * psf总数据里面的key：companyYearSubmits
         *
         * 查询数据条件
         *          entId：企业ID(政务网区ID)
         *
         * 结果需要补充字段
         *          entName：企业名称
         * 返回数据：list
         */
        List<Map<String,Object>> companyYearSubmitsList = null;
        try{
            companyYearSubmitsList = baseDao.mapBySql(GENERATE_THE_DATA_MAP.get("comShareholderContribute")
                    + " WHERE `scjgw_data`.scjgw_nbinfo.entId = '" + entId + "'");
        } catch (Exception e) {
            System.out.println("三、市场监管委-(三)企业年报----加载数据异常:" + e.getMessage());
        }
        if(CollectionUtils.isEmpty(companyYearSubmitsList)){
            companyYearSubmitsList = new ArrayList<>();
        }
        totalMap.put("companyYearSubmits",companyYearSubmitsList);



        /**
         * 对应实体类名称：ZwShebaoInfo
         *
         * 功能模块：四、社保（五险）、社会保险信息-（一）社保信息（五险）-1)养老保险（近三年）
         *
         * psf总数据里面的key：comStaffCultivate
         *
         * 查询数据条件
         *          entName：企业名称
         *          orgCode：组织机构代码
         * 返回数据：list
         */
        List<Map<String,Object>> zwShebaoInfoList = null;
        try{
            zwShebaoInfoList = baseDao.mapBySql(GENERATE_THE_DATA_MAP.get("comStaffCultivate")
                    + " WHERE`sb_data`.shebao_info.marSubName = '" + entName
                    + "' OR `sb_data`.shebao_info.orgCode = '" + orgCode + "'");
        } catch (Exception e) {
            System.out.println("四、社保（五险）、社会保险信息     (1-5)----加载数据异常:" + e.getMessage());
        }
        if(!CollectionUtils.isEmpty(zwShebaoInfoList)){
            zwShebaoInfoList.forEach(l ->l.remove("creditcode"));//移除多余的数据
        } else {
            zwShebaoInfoList = new ArrayList<>();
        }
        totalMap.put("comStaffCultivate",zwShebaoInfoList);


        /**
         * 对应实体类名称：ZwShebaoInfo
         *
         * 功能模块：四、社保（五险）、社会保险信息-（一）社保信息（五险）-2)失业保险（近三年）
         *
         * psf总数据里面的key：comStaffUnemployment
         *
         * 查询数据条件
         *          entName：企业名称
         *          orgCode：组织机构代码
         * 返回数据：list
         */
        totalMap.put("comStaffUnemployment",zwShebaoInfoList);


        /**
         * 对应实体类名称：ZwShebaoInfo
         *
         * 功能模块：四、社保（五险）、社会保险信息-（一）社保信息（五险）-3)医疗保险（近三年）
         *
         * psf总数据里面的key：comStaffMedical
         *
         * 查询数据条件
         *          entName：企业名称
         *          orgCode：组织机构代码
         * 返回数据：list
         */
        totalMap.put("comStaffMedical",zwShebaoInfoList);


        /**
         * 对应实体类名称：ZwShebaoInfo
         *
         * 功能模块：四、社保（五险）、社会保险信息-（一）社保信息（五险）-4)工伤保险（近三年）
         *
         * psf总数据里面的key：comStaffHarm
         *
         * 查询数据条件
         *          entName：企业名称
         *          orgCode：组织机构代码
         * 返回数据：list
         */
        totalMap.put("comStaffHarm",zwShebaoInfoList);


        /**
         * 对应实体类名称：ZwShebaoInfo
         *
         * 功能模块：四、社保（五险）、社会保险信息-（一）社保信息（五险）-5)生育保险（近三年）
         *
         * psf总数据里面的key：comStaffBirth
         *
         * 查询数据条件
         *          entName：企业名称
         *          orgCode：组织机构代码
         * 返回数据：list
         */
        totalMap.put("comStaffBirth",zwShebaoInfoList);

        /**
         * 对应实体类名称：T3070020000013000030(这是表名，不要奇怪)
         *
         * 功能模块：四、社保（五险）、社会保险信息
         *                 （二）社会保险信息
         *
         * psf总数据里面的key：societyInsuranceInfo
         *
         * 查询数据条件
         *          orgNo：企业实名认证时填报的统一社会信用机构代码
         *
         * 返回数据：list
         */
        List<Map<String,Object>> societyInsuranceInfoList = null;
        try{
            societyInsuranceInfoList = baseDao.mapBySql(GENERATE_THE_DATA_MAP.get("societyInsuranceInfo")
                    + "  WHERE `sb_data`.T_3070020000013_000030.F_3070020000013_000030011 = '" + orgNo + "'");
        } catch (Exception e) {
            System.out.println("四、社保（五险）、社会保险信息     （二）社会保险信息----加载数据异常:" + e.getMessage());
        }
        if(CollectionUtils.isEmpty(societyInsuranceInfoList)){
            societyInsuranceInfoList = new ArrayList<>();
        }
        totalMap.put("societyInsuranceInfo",societyInsuranceInfoList);




        /**
         * 对应实体类名称：T3070020000Q03000002(这是表名，不要奇怪)
         *
         * 功能模块：六、用水信息-(一)企业用水信息（近三年）
         *
         * psf总数据里面的key：waterInfo
         *
         * 查询数据条件
         *          entName：企业名称
         *          waterUserNo：用水户号
         * 返回数据：list
         */
        List<Map<String,Object>> waterInfoList = null;
        try{
            waterInfoList = baseDao.mapBySql(GENERATE_THE_DATA_MAP.get("waterInfo")
                    + "  WHERE substr(F_3070020000Q03_000002008,1,4) >= YEAR (now())-2 AND (F_3070020000Q03_000002003 = '"
                    + waterUserNo + "' OR F_3070020000Q03_000002001 = '" + entName + "')");
        } catch (Exception e) {
            System.out.println("六、用水信息-(一)企业用水信息（近三年）----加载数据异常:" + e.getMessage());
        }
        if(CollectionUtils.isEmpty(waterInfoList)){
            waterInfoList = new ArrayList<>();
        }
        totalMap.put("waterInfo",waterInfoList);


        /**
         * 对应实体类名称：T_3070020000Q03_000005(这是表名，不要奇怪)
         *
         * 功能模块：六、用水信息-(二)企业用水欠费信息（近三年）
         *
         * psf总数据里面的key：waterArrears
         *
         * 查询数据条件
         *          entName：企业名称
         *          waterUserNo：用水户号
         * 返回数据：list
         */
        List<Map<String,Object>> waterArrearsList = null;
        try{
            waterArrearsList = baseDao.mapBySql(GENERATE_THE_DATA_MAP.get("waterArrears")
                    + " where substr(F_3070020000Q03_000005008,1,4)>=year(now())-2 and (F_3070020000Q03_000005003= '"
                    + waterUserNo + "' or F_3070020000Q03_000005001='" + entName + "')");
        } catch (Exception e) {
            System.out.println("六、用水信息-(二)企业用水欠费信息（近三年）----加载数据异常:" + e.getMessage());
        }
        if(CollectionUtils.isEmpty(waterArrearsList)){
            waterArrearsList = new ArrayList<>();
        }
        totalMap.put("waterArrears",waterArrearsList);

        /**
         * 对应实体类名称：T_3070020000012_000052(这是表名，不要奇怪)
         *
         * 功能模块：七、政采信息-(一)单一来源
         *
         * psf总数据里面的key：governSource
         *
         * 查询数据条件
         *          entName：企业名称
         * 返回数据：list
         */
        List<Map<String,Object>> governSourceList = null;
        try{
            governSourceList = baseDao.mapBySql(GENERATE_THE_DATA_MAP.get("governSource")
                    + " where F_3070020000012_000052008 = '" + entName + "'");
        } catch (Exception e) {
            System.out.println("七、政采信息-(一)单一来源----加载数据异常:" + e.getMessage());
        }
        if(CollectionUtils.isEmpty(governSourceList)){
            governSourceList = new ArrayList<>();
        }
        totalMap.put("governSource",governSourceList);


        /**
         * 对应实体类名称：F_3070020000012_000007005(这是表名，不要奇怪)
         *
         * 功能模块：七、政采信息-(二)合同公告
         *
         * psf总数据里面的key：governCommon
         *
         * 查询数据条件
         *          entName：企业名称
         * 返回数据：list
         */
        List<Map<String,Object>> governCommonList = null;
        try{
            governCommonList = baseDao.mapBySql(GENERATE_THE_DATA_MAP.get("governCommon")
                    + " where F_3070020000012_000007005 = '" + entName + "'");
        } catch (Exception e) {
            System.out.println("七、政采信息-(二)合同公告----加载数据异常:" + e.getMessage());
        }
        if(CollectionUtils.isEmpty(governCommonList)){
            governCommonList = new ArrayList<>();
        }
        totalMap.put("governCommon",governCommonList);


        /**
         * 对应实体类名称：T_3070020000012_000065(这是表名，不要奇怪)
         *
         * 功能模块：七、政采信息-(三)采购结果公告
         *
         * psf总数据里面的key：governBuy
         *
         * 查询数据条件
         *          entName：企业名称
         * 返回数据：list
         */
        List<Map<String,Object>> governBuyList = null;
        try{
            governBuyList = baseDao.mapBySql(GENERATE_THE_DATA_MAP.get("governBuy")
                    + " where F_3070020000012_000065008 = '" + entName + "'");
        } catch (Exception e) {
            System.out.println("七、政采信息-(三)采购结果公告----加载数据异常:" + e.getMessage());
        }
        if(CollectionUtils.isEmpty(governBuyList)){
            governBuyList = new ArrayList<>();
        }
        totalMap.put("governBuy",governBuyList);



        /**
         * 对应实体类名称：T_3070020000012_000065(这是表名，不要奇怪)
         *
         * 功能模块：七、政采信息-(四)验收信息
         *
         * psf总数据里面的key：governCheck
         *
         * 查询数据条件
         *          entName：企业名称
         * 返回数据：list
         */
        List<Map<String,Object>> governCheckList = null;
        try{
            governCheckList = baseDao.mapBySql(GENERATE_THE_DATA_MAP.get("governCheck")
                    + " where F_3070020000012_000074007 = '" + entName + "'");
        } catch (Exception e) {
            System.out.println("七、政采信息-(四)验收信息----加载数据异常:" + e.getMessage());
        }
        if(CollectionUtils.isEmpty(governCheckList)){
            governCheckList = new ArrayList<>();
        }
        totalMap.put("governCheck",governCheckList);


        /**
         * 对应实体类名称：ZwGfDisEntinfo
         *
         * 功能模块：十、高院信息-(一)高院失信企业信息
         *
         * psf总数据里面的key：courtLoseCompany
         *
         * 查询数据条件
         *          orgNo：企业实名认证时填报的统一社会信用机构代码(取ZwCompany里面的orgNo字段)
         * 返回数据：map
         */
        Map courtLoseCompanyMap = null;
        try{
            courtLoseCompanyMap = baseDao.mapBySqlModel(GENERATE_THE_DATA_MAP.get("courtLoseCompany")
                    + " where orgCode='" + orgNo + "'");
        } catch (Exception e) {
            System.out.println("十、高院信息-(一)高院失信企业信息----加载数据异常:" + e.getMessage());
        }
        totalMap.put("courtLoseCompany",courtLoseCompanyMap);


        /**
         * 对应实体类名称：ZwLegalIllegalCaseStart
         *
         * 功能模块：十、高院信息-(二)高院失信个人信息-1) 企业法定代表人违法信息（立案）
         *
         * psf总数据里面的key：courtLosePersonBegin
         *
         * 查询数据条件
         *          orgNo：企业实名认证时填报的统一社会信用机构代码(取ZwCompany里面的orgNo字段)
         * 返回数据：list
         */
        List<Map<String,Object>> courtLosePersonBeginList = null;
        try{
            courtLosePersonBeginList = baseDao.mapBySql(GENERATE_THE_DATA_MAP.get("courtLosePersonBegin")
                    + " where litName in(select scjgw_personinfo.name from  `scjgw_data`.scjgw_personinfo" +
                    " where `scjgw_data`.scjgw_personinfo.entId = (select scjgw_entinfo.entId from `scjgw_data`.scjgw_entinfo" +
                    " where `scjgw_data`.scjgw_entinfo.creditCode='" + orgNo + "'))");
        } catch (Exception e) {
            System.out.println("十、高院信息-(二)高院失信个人信息-1) 企业法定代表人违法信息（立案）----加载数据异常:" + e.getMessage());
        }
        if(CollectionUtils.isEmpty(courtLosePersonBeginList)){
            courtLosePersonBeginList = new ArrayList<>();
        }
        totalMap.put("courtLosePersonBegin",courtLosePersonBeginList);



        /**
         * 对应实体类名称：ZwLegalIllegalCaseEnd
         *
         * 功能模块：十、高院信息-(二)高院失信个人信息-2) 企业法定代表人违法信息（结案）
         *
         * psf总数据里面的key：courtLosePersonEnd
         *
         * 查询数据条件
         *          orgNo：企业实名认证时填报的统一社会信用机构代码(取ZwCompany里面的orgNo字段)
         * 返回数据：list
         */
        List<Map<String,Object>> courtLosePersonEndList = null;
        try{
            courtLosePersonEndList = baseDao.mapBySql(GENERATE_THE_DATA_MAP.get("courtLosePersonEnd")
                    + " where litName in(select scjgw_personinfo.name from  `scjgw_data`.scjgw_personinfo" +
                    " where `scjgw_data`.scjgw_personinfo.entId = (select scjgw_entinfo.entId from `scjgw_data`.scjgw_entinfo" +
                    " where `scjgw_data`.scjgw_entinfo.creditCode='" + orgNo + "'))");
        } catch (Exception e) {
            System.out.println("十、高院信息-(二)高院失信个人信息-2) 企业法定代表人违法信息（结案）----加载数据异常:" + e.getMessage());
        }
        if(CollectionUtils.isEmpty(courtLosePersonEndList)){
            courtLosePersonEndList = new ArrayList<>();
        }
        totalMap.put("courtLosePersonEnd",courtLosePersonEndList);


        /**
         * 对应实体类名称：ZwGfCaseEndInfo
         *
         * 功能模块：十、高院信息-(三)高院结案信息
         *
         * psf总数据里面的key：courtSettle
         *
         * 查询数据条件
         *          entName：企业名称
         * 返回数据：list
         */
        List<Map<String,Object>> courtSettleList = null;
        try{
            courtSettleList = baseDao.mapBySql(GENERATE_THE_DATA_MAP.get("courtSettle")
                    + " where comName = '" + entName + "'");
        } catch (Exception e) {
            System.out.println("十、高院信息-(三)高院结案信息----加载数据异常:" + e.getMessage());
        }
        if(CollectionUtils.isEmpty(courtSettleList)){
            courtSettleList = new ArrayList<>();
        }
        totalMap.put("courtSettle",courtSettleList);



        /**
         * 对应实体类名称：T3070020000003000034(这是表名，不要奇怪)
         *
         * 功能模块：十一、重要项目列入情况	-	(一)天津市“专精特新”中小企业培育
         *
         * psf总数据里面的key：enrolList1
         *
         * 查询数据条件
         *          orgNo：企业实名认证时填报的统一社会信用机构代码(取ZwCompany里面的orgNo字段)
         * 返回数据：list
         */

        List<Map<String,Object>> enrolList1List = null;
        try{
            enrolList1List = baseDao.mapBySql(GENERATE_THE_DATA_MAP.get("enrolList1")
                    + " where F_3070020000003_000034002='" + orgNo + "'");
        } catch (Exception e) {
            System.out.println("十一、重要项目列入情况-    (一)天津市“专精特新”中小企业培育----加载数据异常:" + e.getMessage());
        }
        if(CollectionUtils.isEmpty(enrolList1List)){
            enrolList1List = new ArrayList<>();
        }
        totalMap.put("enrolList1",enrolList1List);



        /**
         * 对应实体类名称：T_3070020000006_000003(这是表名，不要奇怪)
         *
         * 功能模块：十一、重要项目列入情况	-	(二)天津市自然科学基金项目
         *
         * psf总数据里面的key：enrolList2
         *
         * 查询数据条件
         *          entName：企业名称
         * 返回数据：list
         */
        List<Map<String,Object>> enrolList2List = null;
        try{
            enrolList2List = baseDao.mapBySql(GENERATE_THE_DATA_MAP.get("enrolList2")
                    + " where F_3070020000006_000003002 = '" + entName + "'");
        } catch (Exception e) {
            System.out.println("十一、重要项目列入情况-    (二)天津市自然科学基金项目----加载数据异常:" + e.getMessage());
        }
        if(CollectionUtils.isEmpty(enrolList2List)){
            enrolList2List = new ArrayList<>();
        }
        totalMap.put("enrolList2",enrolList2List);



        /**
         * 对应实体类名称：T_3070020000006_000004(这是表名，不要奇怪)
         *
         * 功能模块：十一、重要项目列入情况	-	(三)国家科技型中小企业名录
         *
         * psf总数据里面的key：enrolList3
         *
         * 查询数据条件
         *          orgNo：企业实名认证时填报的统一社会信用机构代码(取ZwCompany里面的orgNo字段)
         * 返回数据：list
         */
        List<Map<String,Object>> enrolList3List = null;
        try{
            enrolList3List = baseDao.mapBySql(GENERATE_THE_DATA_MAP.get("enrolList3")
                    + " where F_3070020000006_000004006 = '" + orgNo + "'");
        } catch (Exception e) {
            System.out.println("十一、重要项目列入情况-    (三)国家科技型中小企业名录----加载数据异常:" + e.getMessage());
        }
        if(CollectionUtils.isEmpty(enrolList3List)){
            enrolList3List = new ArrayList<>();
        }
        totalMap.put("enrolList3",enrolList3List);


        /**
         * 对应实体类名称：T_3070020000006_000004(这是表名，不要奇怪)
         *
         * 功能模块：十一、重要项目列入情况	-	(四)天津市中小企业“专精特新”产品（接入中）
         *
         * psf总数据里面的key：enrolList4
         *
         * 查询数据条件
         *          orgNo：企业实名认证时填报的统一社会信用机构代码(取ZwCompany里面的orgNo字段)
         * 返回数据：list
         *
         * 暂无数据---接入中......
         *
         */
        List<Map<String,Object>> enrolList4List = null;
        /*try{
            enrolList4List = baseDao.mapBySql(GENERATE_THE_DATA_MAP.get("enrolList4")
                    + " where F_3070020000006_000004006 = '" + orgNo + "'");
        } catch (Exception e) {
            System.out.println("十一、重要项目列入情况-    (四)天津市中小企业“专精特新”产品（接入中）----加载数据异常:" + e.getMessage());
        }*/
        if(CollectionUtils.isEmpty(enrolList4List)){
            enrolList4List = new ArrayList<>();
        }
        totalMap.put("enrolList4",enrolList4List);


        /**
         * 对应实体类名称：T_3070020000006_000011(这是表名，不要奇怪)
         *
         * 功能模块：十一、重要项目列入情况	-	(五)天津市高新技术企业名单
         *
         * psf总数据里面的key：enrolList5
         *
         * 查询数据条件
         *          orgNo：企业实名认证时填报的统一社会信用机构代码(取ZwCompany里面的orgNo字段)
         * 返回数据：list
         */
        List<Map<String,Object>> enrolList5List = null;
        try{
            enrolList5List = baseDao.mapBySql(GENERATE_THE_DATA_MAP.get("enrolList5")
                    + " where F_3070020000006_000011004 = '" + orgNo + "'");
        } catch (Exception e) {
            System.out.println("十一、重要项目列入情况-    (五)天津市高新技术企业名单----加载数据异常:" + e.getMessage());
        }
        if(CollectionUtils.isEmpty(enrolList5List)){
            enrolList5List = new ArrayList<>();
        }
        totalMap.put("enrolList5",enrolList5List);



        /**
         * 对应实体类名称：T_3070020000006_000014(这是表名，不要奇怪)
         *
         * 功能模块：十一、重要项目列入情况	-	(六)天津市自然科学基金资助项目
         *
         * psf总数据里面的key：enrolList6
         *
         * 查询数据条件
         *          entName：企业名称
         * 返回数据：list
         */
        List<Map<String,Object>> enrolList6List = null;
        try{
            enrolList6List = baseDao.mapBySql(GENERATE_THE_DATA_MAP.get("enrolList6")
                    + " where F_3070020000006_000014003 = '" + entName + "'");
        } catch (Exception e) {
            System.out.println("十一、重要项目列入情况-    (六)天津市自然科学基金资助项目----加载数据异常:" + e.getMessage());
        }

        if(CollectionUtils.isEmpty(enrolList6List)){
            enrolList6List = new ArrayList<>();
        }
        totalMap.put("enrolList6",enrolList6List);


        /**
         * 对应实体类名称：T_3070020000006_000014(这是表名，不要奇怪)
         *
         * 功能模块：十一、重要项目列入情况	-	(七)天津市技术先进型服务企业名单
         *
         * psf总数据里面的key：enrolList7
         *
         * 查询数据条件
         *          orgNo：企业实名认证时填报的统一社会信用机构代码(取ZwCompany里面的orgNo字段)
         * 返回数据：list
         */
        List<Map<String,Object>> enrolList7List =null;
        try{
            enrolList7List = baseDao.mapBySql(GENERATE_THE_DATA_MAP.get("enrolList7")
                    + " where F_3070020000006_000030003 = '" + orgNo + "'");
        } catch (Exception e) {
            System.out.println("十一、重要项目列入情况-    (七)天津市技术先进型服务企业名单----加载数据异常:" + e.getMessage());
        }
        if(CollectionUtils.isEmpty(enrolList7List)){
            enrolList7List = new ArrayList<>();
        }
        totalMap.put("enrolList7",enrolList7List);


        /**
         * 对应实体类名称：T_3070020000006_000048(这是表名，不要奇怪)
         *
         * 功能模块：十一、重要项目列入情况	-	(八)国家重点研发计划项目
         *
         * psf总数据里面的key：enrolList8
         *
         * 查询数据条件
         *          orgNo：企业实名认证时填报的统一社会信用机构代码(取ZwCompany里面的orgNo字段)
         * 返回数据：list
         */

        List<Map<String,Object>> enrolList8List = null;
        try{
            enrolList8List = baseDao.mapBySql(GENERATE_THE_DATA_MAP.get("enrolList8")
                    + " where F_3070020000006_000048003 = '" + orgNo + "'");
        } catch (Exception e) {
            System.out.println("十一、重要项目列入情况-    (八)国家重点研发计划项目----加载数据异常:" + e.getMessage());
        }
        if(CollectionUtils.isEmpty(enrolList8List)){
            enrolList8List = new ArrayList<>();
        }
        totalMap.put("enrolList8",enrolList8List);



        /**
         * 对应实体类名称：T_3070020000019_000080(这是表名，不要奇怪)
         *
         * 功能模块：十一、重要项目列入情况	-   (九)天津市龙头企业基本信息
         *
         * psf总数据里面的key：enrolList9
         *
         * 查询数据条件
         *          orgNo：企业实名认证时填报的统一社会信用机构代码(取ZwCompany里面的orgNo字段)
         * 返回数据：list
         */
        List<Map<String,Object>> enrolList9List = null;
        try{
            enrolList9List = baseDao.mapBySql(GENERATE_THE_DATA_MAP.get("enrolList9")
                    + " where F_3070020000019_000080007 = '" + orgNo + "'");
        } catch (Exception e) {
            System.out.println("十一、重要项目列入情况-    (九)天津市龙头企业基本信息----加载数据异常:" + e.getMessage());
        }
        if(CollectionUtils.isEmpty(enrolList9List)){
            enrolList9List = new ArrayList<>();
        }
        totalMap.put("enrolList9",enrolList9List);


        /**
         * 对应实体类名称：T_3070020000035_000002(这是表名，不要奇怪)
         *
         * 功能模块：十二、专利信息
         *
         * psf总数据里面的key：patents
         *
         * 查询数据条件
         *          orgNo：企业实名认证时填报的统一社会信用机构代码(取ZwCompany里面的orgNo字段)
         * 返回数据：list
         */
        List<Map<String,Object>> patentsList = null;
        try{
            patentsList = baseDao.mapBySql(GENERATE_THE_DATA_MAP.get("patents")
                    + " where F_3070020000035_000002008 = '" + orgNo + "'");
        } catch (Exception e) {
            System.out.println("十二、专利信息----加载数据异常:" + e.getMessage());
        }
        if(CollectionUtils.isEmpty(patentsList)){
            patentsList = new ArrayList<>();
        }
        totalMap.put("patents",patentsList);

        PDFReport pdfReport = new PDFReport();
        return pdfReport.saveExportPDF(totalMap,filePath + entName + "/"
                + "天津市地方企业评价报告_"+ zwCompany.getCheckName() +"_" + DateTool.getDateTime2StrByData(date,DateTool.SHORT_DATE_FORMAT_DAY) +".pdf",imgPath);
    }

}
