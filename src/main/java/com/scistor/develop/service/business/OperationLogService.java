/*
package com.scistor.develop.service.business;

import com.scistor.develop.dao.mapper.business.OperationLogMapper;
import com.scistor.develop.data.entity.CompanyAuditOpinion;
import com.scistor.develop.data.entity.OperationLog;
import com.scistor.develop.service.ParentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.scistor.develop.util.SqlUtil.map2where;

@Service
public class OperationLogService extends ParentService {

    @Autowired
    OperationLogMapper operationLogMapper;

    static final String theTableName = "operation_log";

    public int addOperationLog(OperationLog operationLog) {
        return parentRecordInsert(theTableName,operationLog);
    }

    public Map getOperationLogList(OperationLog operationLog,int start, int end,String startTime,String endTime) {
        //return operationLogMapper.listByConditions(theTableName,operationLog);
        Map map = new HashMap<>();
        String sqlLeys = "operModul,operUserName,";
        //调用map生成where的语句
        String[] keys = (sqlLeys).split(",");
        String whereSql = map2where(operationLog, keys);
        whereSql+= StringUtils.isEmpty(startTime) ? (StringUtils.isEmpty(endTime) ? "" : " and date(operation_log.oper_create_time) = '"+ endTime +"'")
                : (StringUtils.isEmpty(endTime) ? (" and date(operation_log.oper_create_time) = '"+ startTime +"'")
                : (" and date(operation_log.oper_create_time) between '" + startTime + "' and '" + endTime + "'"));
        List<OperationLog> list = operationLogMapper.listByConditions(whereSql, start, end, "oper_create_time", operationLog.getOrderDesc());
        map.put("list",list);
        map.put("count",operationLogMapper.countByConditions(whereSql));
        return map;
    }
}
*/
