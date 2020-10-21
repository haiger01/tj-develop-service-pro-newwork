package com.scistor.develop.data;

import static com.scistor.develop.util.ObjectUtil.hump2Line;

public class ParentEntity {
    public String orderCloumn;
    public String orderDesc;

    public String getOrderCloumn() {
        if (orderCloumn == null) return "creater_time";
        if ("updateTime".equals(orderCloumn)) return "update_time";
        if ("dataTime".equals(orderCloumn)) return "data_time";
        return hump2Line(orderCloumn);
    }

    public void setOrderCloumn(String orderCloumn) {
        this.orderCloumn = orderCloumn;
    }

    public String getOrderDesc() {
        if (orderDesc == null) return "desc";
        return hump2Line(orderDesc);
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }
}
