package com.scistor.develop.util.authentication;

public class Constants {
    public static final String REDIS_PHONE_KEY = "TJGL_PHONE_";

    public static final String REDIS_PHONE_KEY_NUM = "TJGL_PHONE_NUM_";

    public static final int REDIS_PHONE_TIME= 60*10*1000;

    public static final int REDIS_PHONE_NUM_TIME= 60*60*1000;

    public static final String REDIS_SEND_ERROR_NUM = "TJRZ_SEND_ERROR_NUM_";


    /**
     * code码表
     */
    public static enum CodeNum {

        Code_200("200","操作成功"),
        Code_500("500", "操作失败"),
        Code_600("600", "登录超时");

        private String code;
        private String description;

        CodeNum(String code,String description){
            this.code = code;
            this.description = description;
        }
        /**
         * 获取成功的code编号
         * @return
         */
        public static String getSucceedCode(){
            return CodeNum.Code_200.code;
        }


        public String getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }

    }

    public enum enrolName {

        Type_1("1","天津市“专精特新”中小企业培育"),
        Type_2("2", "天津市自然科学基金项目"),
        Type_3("3", "国家科技型中小企业名录"),
        Type_4("4", "天津市中小企业“专精特新”产品"),
        Type_5("5", "天津市高新技术企业名单"),
        Type_6("6", "天津市自然科学基金资助项目"),
        Type_7("7", "天津市技术先进型服务企业名单"),
        Type_8("8", "国家重点研发计划项目"),
        Type_9("9", "天津市龙头企业基本信息");

        private String code;
        private String description;

        enrolName(String code,String description){
            this.code = code;
            this.description = description;
        }

        public static String getDescription(String code){
            for(enrolName c : enrolName.values()){
                if(c.code.equals(code)) return c.getDescription();
            }
            return "";
        }



        public String getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }

    }




}
