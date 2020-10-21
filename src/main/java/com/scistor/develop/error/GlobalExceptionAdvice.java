package com.scistor.develop.error;

/**
 * @author huangqiming
 * 全局异常处理类
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

import static com.scistor.develop.tools.BlockAttribute.CODE_NAME;
import static com.scistor.develop.tools.BlockAttribute.MSG_NAME;

@ControllerAdvice           // 注意：ControllerAdvice注解 只拦截Controller 不回拦截 Interceptor的异常
@ResponseBody

public class GlobalExceptionAdvice {

    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionAdvice.class);

    public GlobalExceptionAdvice() {
    }


    /**
     * 全局异常捕捉处理
     * 如果默认抛出Exception异常，错误码为100
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) throws Exception {
        logger.error(ex.getMessage(), ex);
        Map<String, Object> map = new HashMap<>();
        map.put("code", ExceptionCode.code5001.getCode());
        map.put("msg", ExceptionCode.code5001.getMsg());
        return map;
    }

    /**
     * 拦截捕捉自定义异常 MyException.class
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = IidaException.class)
    public Map ifdaErrorHandler(IidaException ex) throws Exception {
        if (Integer.valueOf(ex.getCode()) > 5000) {
            logger.error(ex.getMessage(), ex);
        }
        return this.rtMap(ex);
    }


    public Map rtMap(IidaException exceptionCode) {
        Map<String, Object> map = new HashMap<>();
        map.put(CODE_NAME, exceptionCode.getCode());
        map.put(MSG_NAME, exceptionCode.getMsg());
        return map;
    }

}
