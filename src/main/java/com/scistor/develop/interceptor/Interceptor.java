package com.scistor.develop.interceptor;

import com.alibaba.fastjson.JSON;
import com.scistor.develop.error.ExceptionCode;
import com.scistor.develop.error.IidaException;
import com.scistor.develop.tools.BlockAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.scistor.develop.tools.BlockAttribute.SESSION_KEYNAME_USER_CODE;
import static com.scistor.develop.util.ObjectUtil.logInterceptor;

@Component
public class Interceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(Interceptor.class);

    static List<String> nbkUrls = Arrays.asList(("" +
            "/tianjinDevelop/User/login," +
            "/tianjinDevelop/User/loginTest," +
            "/tianjinDevelop/Dictionary/getInstitutionsList," +
            "/tianjinDevelop/Authorize/entiAuth," +
            "/authorizationCallbak," +
            "/tianjinDevelop/Dictionary/getDictionary," +
            "/tianjinDevelop/NewsDoc/policyList," +
            "/tianjinDevelop/NewsDoc/getById," +
            "/tianjinDevelop/NewsDoc/loadFile," +
            "/tianjinDevelop/NewsDoc/downDoc," +
            "/tianjinDevelop/FinanceInstitute/auth," +
            "/tianjinDevelop/FinanceInstitute/finProId," +
            "/tianjinDevelop/Guarantee/auth," +
            "/tianjinDevelop/FinancingLease/auth," +
            "/tianjinDevelop/Microfinance/auth," +
            "/tianjinDevelop/Guarantee/finProId," +
            "/tianjinDevelop/CompanyCentre/demandList," +
            "/tianjinDevelop/FinanceCentre/resultsShowList," +
            "/tianjinDevelop/CompanyCentre/," +
            "/tianjinDevelop/CompanyCentre/demandDetail," +
            "/tianjinDevelop/User/userRegistered," +
            "/tianjinDevelop/User/sendMsg," +
            "/tianjinDevelop/User/userRegisteredCheck," +
            "/tianjinDevelop/User/userLists," +
            "/tianjinDevelop/CompanyCentre/silverTaxInteractionList," +
            "/tianjinDevelop/Insurance/auth," +
            "/tianjinDevelop/Insurance/finInsuranceId," +
            "/tianjinDevelop/Insurance/applyInsuranceListExecl," +
            "/tianjinDevelop/FinanceInstitute/getKeyProductsList," +
            "/tianjinDevelop/Network/getEnterpriseGovernmentDataList," +
            "/tianjinDevelop/Network/getTest," +
            "/tianjinDevelop/Network/postList," +
            "/tianjinDevelop/Network/generateTheFile," +
            "/tianjinDevelop/Network/generateTheDataDown," +

            "/tianjinDevelop/FinanceCentre/financialAccountingListDown," +
            "/tianjinDevelop/resultsShow/byAreaCodeReleaseDemandList," +
            "/tianjinDevelop/resultsShow/byAreaCodeSolveDemandList," +
            "/tianjinDevelop/User/testUer," +
            "/tianjinDevelop/file/").split(","));


    //这个方法是在访问接口之前执行的
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //if (true) return true;
        String requestURI = request.getRequestURI(); //获取请求的URL

        if (requestURI.indexOf("file") > 0) {
            return true;
        }

        /*if (!logInterceptor(request, log)) {
            throw createError(ExceptionCode.code5002);
        }*/

        if (nbkUrls.contains(request.getRequestURI()))
            return true;

        String userId = request.getSession().getAttribute(SESSION_KEYNAME_USER_CODE) + "";

        //如果session中没有user，表示没登陆
        if (userId == null || userId.equals("null")) {
            //判断上一次是不是被人踢出的登陆状态
            return true;
            //throw createError(ExceptionCode.codeF1);

        }
        return true;  //查询当前的请求为禁止访问的权限资源——放行
    }


    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {


    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }


    public static String commonObj(ExceptionCode exceptionCode) {
        Map map = new HashMap();
        rtMap(exceptionCode, map);
        map.put(BlockAttribute.MAP_NAME, null);
        return JSON.toJSONString(map);
    }

    public static void rtMap(ExceptionCode exceptionCode, Map<String, Object> data) {
        data.put(BlockAttribute.CODE_NAME, exceptionCode.getCode());
        data.put(BlockAttribute.MSG_NAME, exceptionCode.getMsg());
    }

    /**
     * 创建异常格式
     */
    private IidaException createError(ExceptionCode exceptionCode) {
        return new IidaException(exceptionCode.getCode(), exceptionCode.getMsg());
    }
}