package com.scistor.develop.tools;

import java.util.HashMap;
import java.util.Map;

/**
 * @存放系统的公共信息
 */

public final class NewMessageBlockAttribute {

    private NewMessageBlockAttribute() {
    }

    public final static String[] NEWMESSAGE_LIST = ("CERT_STR_COMAPANY_NAME,CERT_STR_COMAPANY_BANK," +
            "CERT_STR_GOODS_NAME,CERT_STR_NUMBER").split(",");

    public final static String[] NEWMESSAGE_LIST2 = ("CERT_STR_COMAPANY_NAME,CERT_STR_COMAPANY_BANK_NAME,CERT_STR_PRODUCT_NAME," +
            "CERT_STR_PRODUCT_ID,CERT_STR_COMPANY_TYPE_NAME").split(",");


    public static Map<String, String> NEWMESSAGE_MAP = new HashMap();
    public static Map<String, String> NEWMESSAGE_MAP2 = new HashMap();


    public static String blockGetNewMessageStr(String strTyp, String... param) {
        String str = NEWMESSAGE_MAP.get(strTyp);
        str = str.replaceAll(NEWMESSAGE_LIST[0], param[0]);
        str = str.replaceAll(NEWMESSAGE_LIST[1], param[1]);
        str = str.replaceAll(NEWMESSAGE_LIST[2], param[2]);
        str = str.replaceAll(NEWMESSAGE_LIST[3], param[3]);
        return str;
    }

    public static String blockGetNewMessageStr2(String msgId, String... param) {
        String msgStr = NEWMESSAGE_MAP2.get(msgId);
        for (int i = 0; i < NEWMESSAGE_LIST2.length; i++) {
            msgStr = msgStr.replaceAll(NEWMESSAGE_LIST2[i], param[i]);
        }
        return msgStr;
    }
}
