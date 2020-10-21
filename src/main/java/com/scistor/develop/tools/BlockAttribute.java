package com.scistor.develop.tools;

import com.scistor.develop.util.RSAUtil;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;

import static com.scistor.develop.util.ObjectUtil.ckStrIsNotnull;

/**
 * @存放系统的公共信息
 */

public final class BlockAttribute {

    private BlockAttribute() {
    }

    public final static String PROJECT_NAME = "Ifcert Data Analysis";          // 项目名

    public final static String CODE_NAME = "code";          // code参数名

    public final static String MSG_NAME = "msg";            // msg参数名

    public final static String LIST_NAME = "list";          // list参数名

    public final static String MAP_NAME = "map";            // map参数名

    public final static String DATA_NAME = "data";          // map参数名

    public final static String ACCESS_DATA_NAME = "body";          // map参数名

    public final static String[] SQL_STR = {"insert", "select", "delete", "update", "count", "truncate", "declare"};

    public final static String INVESTOR_PRODUCTID_REDIS_KEY = "investor_completion_investment_productId";

    //用户姓名
    public final static String SESSION_KEYNAME_USER_NAME = "username";
    //用户联系方式
    public final static String SESSION_KEYNAME_USER_TEL = "usertel";
    //用户code
    public final static String SESSION_KEYNAME_USER_CODE = "usercode";
    //公司名
    public final static String SESSION_KEYNAME_COMPANY_NAME = "companyName";
    //社会信用码
    public final static String SESSION_KEYNAME_CREDIT_CODE = "creditCode";
    //企业ID
    public final static String SESSION_KEYNAME_COMPANY_ID = "companyId";
    //组织编码
    public final static String SESSION_KEYNAME_ORG_NUMBER = "orgNumber";
    //用户地址
    public final static String SESSION_KEYNAME_REG_ADD_INFO = "regAddInfo";
    //公司类型
    public final static String SESSION_KEYNAME_COMPANY_TYPE = "companyType";
    //公司类型
    public final static String SESSION_KEYNAME_FINOPERATE = "finOperate";

    //是否是项目加团队企业
    public final static String SESSION_KEYNAME_ISTEAM = "isTeam";

    public final static String SESSION_KEYNAME_USER_TOKEN = "accessToken";

    public final static String SESSION_KEYNAME_USER_DEPARTENT = "department";

    public final static String SESSION_KEYNAME_PROVINCE_CODE = "provinceCode";

    public final static String SESSION_KEYNAME_CITY_CODE = "cityCode";

    public static String CASESQL  = "CASE WHEN TABLENAME.id IN (SELECT finpro_id FROM loan_application WHERE loan_type = 1 AND finpro_id != '' AND finpro_id IS NOT NULL AND delete_flag = DELETEFLAG_COMPANTID GROUP BY finpro_id) THEN 1 ELSE 0 END yes_no_apply,";

    public final static String[] SESSION_KEYNAME_LIST = ("username,usertel,usercode,companyName,creditCode," +
            "companyId,orgNumber,regAddInfo,companyType,finOperate,department").split(",");


    public final static String PRAENT_ID = "id";
    public final static String PRAENT_USERCODE = "usercode";
    public final static String PRAENT_ACCEPTCOMPANYID = "acceptCompanyId";
    public final static String PRAENT_COMPANYNAME = "companyName";
    public final static String PRAENT_COMPANYID = "companyId";
    public final static String PRAENT_COMPANYTYPE = "companyType";
    public final static String PRAENT_CREATERUSER = "createrUser";
    public final static String PRAENT_CREATERTIME = "createrTime";
    public final static String PRAENT_UPDATETIME = "updateTime";
    public final static String PRAENT_DELETEFLAG = "deleteFlag";
    public final static String PRAENT_TEL = "tel";
    public final static String PRAENT_FIXEDTEL = "fixedTel";

    //标识符
    public final static String SESSION_KEYNAME_IDENTIFIER = "identifier";
    //是否首次登录，值：0-否，1-是
    public final static String SESSION_KEYNAME_ISFIRSTLOGIN = "isFirstLogin";
    //最后登录时间
    public final static String SESSION_KEYNAME_LASTLOGONTIME = "lastLogonTime";
    //备注
    public final static String SESSION_KEYNAME_REMARK = "remark";
    //树形结构权限列表json串
    public final static String SESSION_KEYNAME_MENUS = "menus";

    //拦截器后拦截
    public final static List<String> AFTER_URI_LIST = Arrays.asList(("" +
            "" +
            "" +
            "").split(","));

    public static String RSA_PUBLICKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDM63KAPH476o / Y2YiECae3LMX2NMfC3x9w / XWz\n" +
            "        BKwPmNR9Mi7Yb + qA9DnighR9qzRFJng3rKflBCuy23QJX / AcrO64KyuRyN / VwA / 0 + 89 ccwMblLr +\n" +
            "                0 nRTpacjsKGYGb + YMp4tLcM2RzyWNo + P1LGG6aZE0K7sAwm47d6PpwstYwIDAQAB";

    public static String RSA_PRIVATEKEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMzrcoA8fjvqj9jZiIQJp7csxfY0\n" +
            "        x8LfH3D9dbMErA+Y1H0yLthv6oD0OeKCFH2rNEUmeDesp+UEK7LbdAlf8Bys7rgrK5HI39XAD/T7\n" +
            "        z1xzAxuUuv7SdFOlpyOwoZgZv5gyni0twzZHPJY2j4/UsYbppkTQruwDCbjt3o+nCy1jAgMBAAEC\n" +
            "        gYAiedJPxN2wEuyUnTSckktHRB8iJwscvBE8H0h4D+cq0JmfXpwHCf04KN68PwpXgkBPCgPbyg6N\n" +
            "        N0Bq3QUOtrdcrpcV7qVnX8dnp/w272dBN3VfB4+Nl4A3apBaou1VdUl+dktgv0ltC+4snzLhBIYy\n" +
            "        vERBCinfdN7qqH7cN+4IYQJBAOzAS4IB35ckfY1FXH96C/rnlwg1dZ1jNDlaFnhn2VKUP2lhP/m6\n" +
            "        wkFIieqeoQHZ4FAiuh0YfkfaYkHjjlflzjkCQQDdlJ4HNeh/Hz2mvowkhNmfqDTj2ohwmhY17hv0\n" +
            "        2RqFQUUx9EbmznKBfYgEEf129t9tGSx5SiTDcRo55i9yqdh7AkAuXNHsmbVRgeHcYnLku9/QuL2I\n" +
            "        6/8mXSEDAjNYTO3wD/wDBPpkS0BzTGNFwN7C4AnfZ42O2RcRtyB6yrc7W1NxAkAD3U6wQ4Ftjibu\n" +
            "        HI6nKGX/QdjCTJJqnFVc1W1JZOvwfQXu7Bq8dcdLeNwBbirkg0O8TF8ZMnDXs8ZIUjprnoi3AkEA\n" +
            "        vrXFcuAE6YkNfQORMVy01ybpEKVbzEcr2Ep9jbXo8gRmIBm4HrupPl8gnfj3PwV0tZlH8mBs2Kcs\n" +
            "        OXl3qvk/kw==";

    public static List NOT_RSA_URL = new ArrayList();


    //展销
    public static final String RSA_PRIVATEKEY2 = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMWgGguIDNtnh23F7XM0T1kaliKoZ4s2DRDeCfHsmKvDvcN3SA5zKgZnXzwsjDP3CEpDq+flokL3QOvRMl/hYoRQLCMpwrL4YvLo8tDCQbm0NfUjosZHQgnot+CqqyQ5W36NXNSkYSSSBKEfwrr/BZ4cBTPCNgudSxdK3zPoImYzAgMBAAECgYEArEPl0B10z5/MHnUEiYKUlCAntOhE8cVY2FYkJxzM7BTBCmykg+wBOvnKuyI9yGJSeKHKbGbv7R2oGqcNGSTXAOG+1C8IL5mQMLp7TyDiDAlXVjqQ0KMvrXlR2LyB/VdoRDpYyXlQVZvxSVPGlB7BjJc/Zo960L5OLEeGQa7gHhkCQQD/HNfX717Vznj6Tp6AWAFs/79QFpLKS5FDfM8mA7/HglJF267k2jpCil0jH4ueX5CPV27GZQNu3kqxE3+BFO7fAkEAxlASJy0Elc17G5VnzZDOzuE2QvAPbE8e6bvr/sFri7PPW6Sjw8at4lZenl2uc15Hn3SKVWv6mkaEj8g/rK23LQJAeVd6wta0QPYITOaANYOmifXLRYi3IUl4OqVu0iIOYhL2lwu60FeMRoIRctdaTWYfGE75/ZumWmGZHUis6PTP+QJBAJDCXC3qp6EuKEIfUnF01fGf5fsAv1FhhAA8I+2HhRiqPZ/4024SdlV0lIbnfXNLi96ytFPciUbaWOOX2hsTh1UCQQDMXqJDmTPkdsLa75xynrYz2JO6GQdyGHcwWREPm+0kBpsYTS6OJ0C4qN+oi9LTF92FiS2wHkV4U1Op/6GcqKAs";

    public static final String RSA_PUBICKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDFoBoLiAzbZ4dtxe1zNE9ZGpYiqGeLNg0Q3gnx7Jirw73Dd0gOcyoGZ188LIwz9whKQ6vn5aJC90Dr0TJf4WKEUCwjKcKy+GLy6PLQwkG5tDX1I6LGR0IJ6LfgqqskOVt+jVzUpGEkkgShH8K6/wWeHAUzwjYLnUsXSt8z6CJmMwIDAQAB";

    public static Map<String,String> LOAN_RATE_MAP = new  HashMap<String,String>(){
        {
            this.put("1","不低于1");
            this.put("2","不超过2");
            this.put("3","3左右");
            this.put("4","固定利率4");
            this.put("5","范围5");
            this.put("6","其他");
        }
    };


    public static Map<String,String> COMPANY_TYPE_MAP = new  HashMap<String,String>(){
        {
            this.put("yinhang","3");
            this.put("danbao","5");
            this.put("xiaodai","6");
            this.put("zulin","7");
            this.put("baoxian","8");
            this.put("guquan","9");
            this.put("zhuanyuan","10");
            this.put("chushi","11");
            this.put("3","yinhang");
            this.put("4","jinrongju");
            this.put("5","danbao");
            this.put("6","xiaodai");
            this.put("7","zulin");
            this.put("8","baoxian");
            this.put("9","guquan");
            this.put("10","zhuanyuan");
            this.put("11","chushi");
            this.put("3","银行");
            this.put("4","金融局");
            this.put("5","担保");
            this.put("6","小贷");
            this.put("7","租赁");
            this.put("8","保险");
            this.put("9","股权");
            this.put("10","专员");
            this.put("11","处室");
        }
    };


    public static Map<String,String> COMPANY_TYPE_NAME_MAP = new  HashMap<String,String>(){
        {
            this.put("银行","3");
            this.put("金融局","4");
            this.put("担保","5");
            this.put("小贷","6");
            this.put("租赁","7");
            this.put("保险","8");
            this.put("股权","9");
            this.put("专员","10");
            this.put("处室","11");
            this.put("3","银行");
            this.put("4","金融局");
            this.put("5","担保");
            this.put("6","小贷");
            this.put("7","租赁");
            this.put("8","保险");
            this.put("9","股权");
            this.put("10","专员");
            this.put("11","处室");
        }
    };

    public static List<String> GENERATE_THE_DATA_List = new ArrayList<String>(){
        {
            this.add("company");
            this.add("business");
            this.add("comPerson");
            this.add("comShareholder");
            this.add("comShareholderContribu");
            this.add("comShareholder");
            this.add("companyYearSubmits");
            this.add("comStaffCultivate");
            this.add("comStaffUnemployment");
            this.add("comStaffMedical");
            this.add("comStaffHarm");
            this.add("comStaffBirth");
            this.add("waterInfo");
            this.add("waterArrears");
            this.add("governSource");
            this.add("governCommon");
            this.add("governBuy");
            this.add("governCheck");
            this.add("courtLoseCompany");
            this.add("courtLosePersonBegin");
            this.add("courtLosePersonEnd");
            this.add("courtSettle");
            this.add("enrolList1");
            this.add("enrolList2");
            this.add("enrolList3");
            this.add("enrolList4");
            this.add("enrolList5");
            this.add("enrolList6");
            this.add("enrolList7");
            this.add("enrolList8");
            this.add("enrolList9");
            this.add("patents");
        }
    };


    /**
     * GenerateTheData
     * 政务网数据sql--生成pdf文件
     *
     */
    public static Map<String,String> GENERATE_THE_DATA_MAP = new  HashMap<String,String>(){
        {
            //一、企业基本信息(企业填报)
            this.put("company","aaaaa");
            //二、工商信息
            this.put("business","SELECT entId,entName,domDistrict,creditCode orgNo,entType,industryCo,regCap,estDate,lerep,tel,regOrg,regState,regNo,dom,opScope,lerep_tel FROM `scjgw_data`.scjgw_entinfo ");
            //三、市场监管委-(一)企业人员信息
            this.put("comPerson","SELECT scjgw_personinfo.entId,scjgw_personinfo.name,scjgw_personinfo.position FROM `scjgw_data`.scjgw_personinfo ");
            //三、市场监管委-(二)股东信息-1）股东出资记录
            this.put("comShareholderContribute","SELECT `scjgw_data`.scjgw_gdczinfo.entId,`scjgw_data`.scjgw_gdczinfo.inv,`scjgw_data`.scjgw_gdczinfo.conDate,`scjgw_data`.scjgw_gdczinfo.conFrom,`scjgw_data`.scjgw_gdczinfo.conNam,`scjgw_data`.scjgw_gdczinfo.invType FROM `scjgw_data`.scjgw_gdczinfo ");
            //三、市场监管委-(二)股东信息-2）股东变更信息
            this.put("comShareholder","SELECT `scjgw_data`.scjgw_gdbginfo.entId,`scjgw_data`.scjgw_gdbginfo.inv,`scjgw_data`.scjgw_gdbginfo.transAmprBefore,`scjgw_data`.scjgw_gdbginfo.transAmprAfter,`scjgw_data`.scjgw_gdbginfo.altDate,`scjgw_data`.scjgw_gdbginfo.ancheYear FROM `scjgw_data`.scjgw_gdbginfo WHERE entId = ");
            //三、市场监管委-(三)企业年报
            this.put("companyYearSubmits","SELECT `scjgw_data`.scjgw_nbinfo.entId,`scjgw_data`.scjgw_nbinfo.email,`scjgw_data`.scjgw_nbinfo.addr,`scjgw_data`.scjgw_nbinfo.assGro,`scjgw_data`.scjgw_nbinfo.liaGro,`scjgw_data`.scjgw_nbinfo.totEqu,`scjgw_data`.scjgw_nbinfo.vendInc,`scjgw_data`.scjgw_nbinfo.maiBusInc,`scjgw_data`.scjgw_nbinfo.proGro,`scjgw_data`.scjgw_nbinfo.ratGro,`scjgw_data`.scjgw_nbinfo.netInc,`scjgw_data`.scjgw_nbinfo.ancheYear FROM `scjgw_data`.scjgw_nbinfo ");
            //四、社保（五险）、社会保险信息-（一）社保信息（五险）-1)养老保险（近三年）
            this.put("comStaffCultivate","SELECT * FROM `sb_data`.shebao_info ");
            //四、社保（五险）、社会保险信息-（一）社保信息（五险）-2)失业保险（近三年）
            this.put("comStaffUnemployment","aaaaa");
            //四、社保（五险）、社会保险信息-（一）社保信息（五险）-3)失业保险（近三年）
            this.put("comStaffMedical","aaaaa");
            //四、社保（五险）、社会保险信息-（一）社保信息（五险）-4)失业保险（近三年）
            this.put("comStaffHarm","aaaaa");
            //四、社保（五险）、社会保险信息-（一）社保信息（五险）-5)失业保险（近三年）
            this.put("comStaffBirth","aaaaa");
            //四、社保（五险）、社会保险信息-（二）社会保险信息
            this.put("societyInsuranceInfo","SELECT F_3070020000013_000030002,F_3070020000013_000030004,F_3070020000013_000030010,F_3070020000013_000030011,F_3070020000013_000030017,F_3070020000013_000030021,F_3070020000013_000030025,F_3070020000013_000030028,F_3070020000013_000030031 FROM `sb_data`.T_3070020000013_000030 ");
            //六、用水信息-(一)企业用水信息（近三年）
            this.put("waterInfo","SELECT F_3070020000Q03_000002008,F_3070020000Q03_000002009,F_3070020000Q03_000002011,F_3070020000Q03_000002014 FROM `sb_data`.T_3070020000Q03_000002 ");
            //六、用水信息-(二)企业用水欠费信息（近三年）
            this.put("waterArrears","select F_3070020000Q03_000005008,F_3070020000Q03_000005009 from `sb_data`.T_3070020000Q03_000005 ");
            //七、政采信息-(一)单一来源
            this.put("governSource","select F_3070020000012_000052003,F_3070020000012_000052004,F_3070020000012_000052006 from `sb_data`.T_3070020000012_000052 ");
            //七、政采信息-(二)合同公告
            this.put("governCommon","select F_3070020000012_000007008,F_3070020000012_000007004,F_3070020000012_000007007,F_3070020000012_000007011,F_3070020000012_000007009,F_3070020000012_000007001 from `sb_data`.T_3070020000012_000007 ");
            //七、政采信息-(三)采购结果公告
            this.put("governBuy","select F_3070020000012_000065004,F_3070020000012_000065005,F_3070020000012_000065006,F_3070020000012_000065007,F_3070020000012_000065010,F_3070020000012_000065020,F_3070020000012_000065023 from `sb_data`.T_3070020000012_000065 ");
            //七、政采信息-(四)验收信息
            this.put("governCheck","select F_3070020000012_000074002,F_3070020000012_000074003,F_3070020000012_000074004,F_3070020000012_000074005,F_3070020000012_000074006,F_3070020000012_000074007,F_3070020000012_000074008 from `sb_data`.T_3070020000012_000074 ");
            //十、高院信息-(一)高院失信企业信息
            this.put("courtLoseCompany","select exedComName,exeCaseNumber,exeBasSymbol,exedComAddress,orgCode,legPerson,exeCase,subAmount from `gf_data`.gf_dis_entinfo ");
            //十、高院信息-(二)高院失信个人信息-1) 企业法定代表人违法信息（立案）
            this.put("courtLosePersonBegin","select caseStartDate,caseNumber,caseCategory,`case`,litName,idNumber,gender,position,caseSource from `gf_data`.legal_illegal_case_start ");
            //十、高院信息-(二)高院失信个人信息-2) 企业法定代表人违法信息（结案）
            this.put("courtLosePersonEnd","select caseEndDate,caseNumber,`case`,litName,idNumber,gender,position,litStatus,caseEndSubject,caseEndMode,judResult from `gf_data`.legal_illegal_case_end ");
            //十、高院信息-(三)高院结案信息
            this.put("courtSettle","select caseId,caseArea,caseNumber,comName,comNumber,legPerson,legPerIdNumber,litStatus,`case`,judResult,caseEndDate,caseEndMode from `gf_data`.gf_case_end_info ");
            //十一、重要项目列入情况	-	(一)天津市“专精特新”中小企业培育
            this.put("enrolList1","select F_3070020000003_000034001,F_3070020000003_000034003,F_3070020000003_000034005,F_3070020000003_000034006 from `sb_data`.T_3070020000003_000034 ");
            //十一、重要项目列入情况	-	(二)天津市自然科学基金项目
            this.put("enrolList2","select F_3070020000006_000003005,F_3070020000006_000003004,F_3070020000006_000003002,F_3070020000006_000003000,F_3070020000006_000003001,F_3070020000006_000003003 from `sb_data`.T_3070020000006_000003 ");
            //十一、重要项目列入情况	-	(三)国家科技型中小企业名录
            this.put("enrolList3","select F_3070020000006_000004000,F_3070020000006_000004002,F_3070020000006_000004006,F_3070020000006_000004007 from `sb_data`.T_3070020000006_000004 ");
            //十一、重要项目列入情况	-	(四)天津市中小企业“专精特新”产品（接入中）
            this.put("enrolList4","aaaaa");
            //十一、重要项目列入情况	-	(五)天津市高新技术企业名单
            this.put("enrolList5","select F_3070020000006_000011002,F_3070020000006_000011003 from `sb_data`.T_3070020000006_000011 ");
            //十一、重要项目列入情况	-	(六)天津市自然科学基金资助项目
            this.put("enrolList6","select F_3070020000006_000014002,F_3070020000006_000014005,F_3070020000006_000014003,F_3070020000006_000014006,F_3070020000006_000014004,F_3070020000006_000014007 from `sb_data`.T_3070020000006_000014 ");
            //十一、重要项目列入情况	-	(七)天津市技术先进型服务企业名单
            this.put("enrolList7","select F_3070020000006_000030002,F_3070020000006_000030003,F_3070020000006_000030004,F_3070020000006_000030005,F_3070020000006_000030006 from  `sb_data`.T_3070020000006_000030 ");
            //十一、重要项目列入情况	-	(八)国家重点研发计划项目
            this.put("enrolList8","select F_3070020000006_000048007,F_3070020000006_000048008,F_3070020000006_000048002,F_3070020000006_000048004,F_3070020000006_000048005,F_3070020000006_000048006 from `sb_data`.T_3070020000006_000048 ");
            //十一、重要项目列入情况	-	(九)天津市龙头企业基本信息
            this.put("enrolList9","select F_3070020000019_000080001,F_3070020000019_000080002,F_3070020000019_000080003,F_3070020000019_000080006 from `sb_data`.T_3070020000019_000080 ");
            //十二、专利信息
            this.put("patents","select F_3070020000035_000002007,F_3070020000035_000002012,F_3070020000035_000002008,F_3070020000035_000002002,F_3070020000035_000002013,F_3070020000035_000002011 from `sb_data`.T_3070020000035_000002 ");
        }
    };


    /**
     * 根据统一认证平台返回信息判断用户等级
     */
    public enum CompanyType {
        NOT_SUPPORT("0"),
        QY("1"),
        YH("3"),
        JRJ("4"),
        DB("5"),
        XD("6"),
        ZL("7"),
        BX("8"),
        GQ("9"),
        ZY("10"),
        CS("11"),
        ADMIN("-1");

        private String value;

        private CompanyType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static CompanyType getCompanyType(String department) {
            if("".equals(department) || department == null) department = "";
            CompanyType companyType = null;
            switch (department) {
                case "":
                    companyType = CompanyType.QY;
                    break;
                case "yinhang":
                    companyType = CompanyType.YH;
                    break;
                case "jinrong":
                case "caiwu":
                case "jinrongju":
                case "shijinrongju":
                case "qujinrongju":
                    companyType = CompanyType.JRJ;
                    break;
                case "danbao":
                    companyType = CompanyType.DB;
                    break;
                case "xiaodai":
                    companyType = CompanyType.XD;
                    break;
                case "zulin":
                    companyType = CompanyType.ZL;
                    break;
                case "baoxian":
                    companyType = CompanyType.BX;
                    break;
                case "guquan":
                    companyType = CompanyType.GQ;
                    break;
                case "zhuanyuan":
                    companyType = CompanyType.ZY;
                    break;
                case "chushi":
                    companyType = CompanyType.CS;
                    break;
                case "superadmin":
                    companyType = CompanyType.ADMIN;
                    break;
                default:
                    companyType = CompanyType.NOT_SUPPORT;
            }
            return companyType;
        }
    }


    /**
     *  审核流程  成功状态  失败状态
     *  信息初审	    2       4	    审核中
     *  数据审核	    5			    审核中
     *  贷前调查	    4.01    4.1		审核中
     *  最终审核	    5.1     7		审核中
     *  实际放款	    7.5			    审核中
     *  实际放款	    8			    审核完成
     *
     *  审核失败     4，4.1，7       审核失败
     *
     *  9 拒绝放款   这个应该是没有了！！！！
     *
     *  不适用的但是要兼容的状态---旧数据问题，新数据不存在，金融局账户撮合的搜索状态时前台传的---后期修改
     *  5.1(现场调查)————>6 授信通过状态
     *  5.1现场调查————>5.2通过资料收集————>4.2拒绝资料收集
     *  5.2资料收集————>5.3通过银行开户————>4.3拒绝银行开户
     *  5.3银行开户————>6系统生成上报时间，待授信————>无法拒绝
     *  如果是6待授信————>7.5通过确认授信————>7确认拒绝授信
     *
     */

    /**
     * 获取审核状态
     */
    public enum CheckType {
        NOT_SUPPORT("0"),                               //表示为错误阶段的进度--或者是非法,不支持的类型
        //审核进度
        FIRST_TRIAL("2,4"),                             //信息初审first trial       1_
        REVIEW("5"),                                    //数据审查review            2_
        SURVEY("4.01,4.1"),                             //贷前调查survey            3_
        AUDIT("5.1,7"),                                 //最终审核audit             4_
        LENDING("7.5,8,9"),                             //实际放款lending           5_

        //审核状态in review
        IN_REVIEW("2,5,4.01,5.1,7.5"),                  //审核中                     _1
        REVIEW_SUCCESS("8"),                            //审核成功                   _2
        REVIEW_LOSE("4,4.1,7"),                         //审核失败                   _3

        //有审核状态也有审核进度--审核成功只有最中审核有
        FIRST_TRIAL_IN_REVIEW("2"),                     //信息初审+审核中            1_1
        FIRST_TRIAL_REVIEW_LOSE("4"),                   //信息初审+审核失败          1_3
        REVIEW_IN_REVIEW("5"),                          //数据审查+审核中            2_1
        SURVEY_IN_REVIEW("4.01"),                       //贷前调查+审核中            3_1
        SURVEY_REVIEW_LOSE("4.1"),                      //贷前调查+审核失败          3_3
        AUDIT_IN_REVIEW("5.1"),                         //最终审核+审核中            4_1
        AUDIT_REVIEW_LOSE("7"),                         //最终审核+审核失败          4_3
        LENDING_IN_REVIEW("7.5"),                       //实际放款+审核中            5_1
        LENDING_REVIEW_SUCCESS("8"),                    //实际放款+审核成功          5_2
        LENDING_REVIEW_LOSE("9"),                       //实际放款+审核失败          5_3
        OTHER_COMBINATIONS("-1");                       //其他组合--例如：1_2,这阶段不存在的都表示-1
                                                        //有1_2,2_2,2_3,3_2,4_2

        /**
         * 1        不等于4,4.1,7,8（2,5,4.01,5.1,7.5）
         * 2        8
         * 3        4,4.1,7
         */

        /**
         *  只有进度
         *  1_   :2,4
         *  2_   :5
         *  3_   :4.01,4.1
         *  4_   :5.1,7
         *  5_   :7.5,8,9
         *
         *  只有审核状态
         *  _1   :2,5,4.01,5.1,7.5
         *  _2   :8
         *  _3   :4,4.1,7
         *
         *  有进度也有状态
         *  1_1  :2
         *  1_3  :4
         *  2_1  :5
         *  3_1  :4.01
         *  3_3  :4.1
         *  4_1  :5.1
         *  4_3  :7
         *  5_1  :7.5
         *  5_2  :8
         *  5_3  :9
         *  其他 ： -1表示没有数据
         */

        private String value;

        private CheckType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static CheckType getCheckType(String reviewProgress,String reviewStatus) {
            if(!ckStrIsNotnull(reviewProgress)) reviewProgress = "";
            if(!ckStrIsNotnull(reviewStatus)) reviewStatus = "";
            CheckType checkType = null;
            switch (reviewProgress + "_" + reviewStatus) {
                case "1_":
                    checkType = CheckType.FIRST_TRIAL;
                    break;
                case "2_":
                    checkType = CheckType.REVIEW;
                    break;
                case "3_":
                    checkType = CheckType.SURVEY;
                    break;
                case "4_":
                    checkType = CheckType.AUDIT;
                    break;
                case "5_":
                    checkType = CheckType.LENDING;
                    break;
                case "_1":
                    checkType = CheckType.IN_REVIEW;
                    break;
                case "_2":
                    checkType = CheckType.REVIEW_SUCCESS;
                    break;
                case "_3":
                    checkType = CheckType.REVIEW_LOSE;
                    break;
                case "1_1":
                    checkType = CheckType.FIRST_TRIAL_IN_REVIEW;
                    break;
                case "1_3":
                    checkType = CheckType.FIRST_TRIAL_REVIEW_LOSE;
                    break;
                case "2_1":
                    checkType = CheckType.REVIEW_IN_REVIEW;
                    break;
                case "3_1":
                    checkType = CheckType.SURVEY_IN_REVIEW;
                    break;
                case "3_3":
                    checkType = CheckType.SURVEY_REVIEW_LOSE;
                    break;
                case "4_1":
                    checkType = CheckType.AUDIT_IN_REVIEW;
                    break;
                case "4_3":
                    checkType = CheckType.AUDIT_REVIEW_LOSE;
                    break;
                case "5_1":
                    checkType = CheckType.LENDING_IN_REVIEW;
                    break;
                case "5_2":
                    checkType = CheckType.LENDING_REVIEW_SUCCESS;
                    break;
                case "5_3":
                    checkType = CheckType.LENDING_REVIEW_LOSE;
                    break;
                case "1_2":
                case "2_2":
                case "2_3":
                case "3_2":
                case "4_2":
                    checkType = CheckType.OTHER_COMBINATIONS;
                    break;
                default:
                    checkType = CheckType.NOT_SUPPORT;
            }
            return checkType;
        }
    }

}
