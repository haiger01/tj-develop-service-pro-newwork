package com.scistor.develop.tools;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.scistor.develop.util.ObjectUtil.amount2Str;

public final class DictionaryTools {
    private DictionaryTools() {
    }

    public static Map<String, Object> DICTIONARY_MAP = new HashMap() {{
        this.put("$companyNum$", 0);
        this.put("$bankNum$", 0);
        this.put("$proGoodsNum$", 0);
        this.put("$loanDemandNum$", 0);
        this.put("$loanDemandAmount$", 0);
        this.put("$loanDemandCompanyNum$", 0);
        this.put("$secussAmount$", 0);
        this.put("$companyDataNum$", 0);
    }};

    //修改字典表的值
//    public static String mergeDictionaryStr(String str) {
//        for (String key : DICTIONARY_MAP.keySet()) {
//            String val = DICTIONARY_MAP.get(key) + "";
//            str = str.replace(key, amount2Str(val));
//        }
//        return str;
//    }

    //修改字典表的值
    public static String mergeDictionaryStr(String str) {
        for (String key : DICTIONARY_MAP.keySet()) {
            String val = DICTIONARY_MAP.get(key) + "";
            if (str.equals("帮助$loanDemandCompanyNum$家企业解决$secussAmount$万元融资")) {
                System.out.println(val);
            }
            if (str.indexOf(key) > 0 && val.length() > 4) {
                val = val.substring(0, val.length() - 4) + "亿";
                str = str.replace("万", "");
            }

            str = str.replace(key, val);
        }
        return str;
    }

    public static void main(String[] args) {


    }


}
