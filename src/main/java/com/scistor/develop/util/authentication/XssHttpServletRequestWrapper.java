package com.scistor.develop.util.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 防御xss攻击的规则过滤
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

	public XssHttpServletRequestWrapper(HttpServletRequest servletRequest) {  
        super(servletRequest);  
    }  
	
    public String[] getParameterValues(String parameter) {  
      String[] values = super.getParameterValues(parameter);  
      if (values==null)  {  
                  return null;  
          }  
      int count = values.length;  
      String[] encodedValues = new String[count];  
      for (int i = 0; i < count; i++) {  
                 encodedValues[i] = cleanXSS(values[i]);  
       }  
      return encodedValues;  
    }  
    public String getParameter(String parameter) {  
          String value = super.getParameter(parameter);  
          if (value == null) {  
                 return null;  
                  }  
          return cleanXSS(value);  
    }  
    public String getHeader(String name) {  
        String value = super.getHeader(name);  
        if (value == null)  
            return null;  
        return cleanXSS(value);  
    }  
    /**
     * 给不被信任的数据添加过滤规则
     * @param value - 需要添加规则的数据
     * @return
     */
    private String cleanXSS(String value) {  
        value = value.replaceAll("(?i)<script>", "");  
        value = value.replaceAll("(?i)</script>", ""); 
        value = value.replaceAll("(?i)<script", "");  
        value = value.replaceAll("(?i)</script", "");  
        value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");  
        value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");  
        value = value.replaceAll("\"", "&#34;");  
        value = value.replaceAll("'", "&#39;");  
        value = value.replaceAll("eval\\((.*)\\)", "");  
        value = value.replaceAll("[\\\"\\\'][\\s]*((?i)javascript):(.*)[\\\"\\\']", "\"\"");  
//        value = value.replaceAll("script", "");  
        return value;  
    }  
	
}
