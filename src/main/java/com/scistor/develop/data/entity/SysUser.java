package com.scistor.develop.data.entity;

import java.util.Date;

/**
 * 登录用户对象
 * @GongWei
 */
public class SysUser {

    public String oppid; //用户id
    public String username;    //用户名
    public String identifier;  //标识符
    public String phone;   //绑定的手机号
    public String isFirstLogin;    //是否首次登录，值：0-否，1-是
    public Date lastLogonTime;     //最后登录时间
    public String remark;  //备注
    public String menus;   //树形结构权限列表json串
    public String department;   //部门

    public String getOppid() {
        return oppid;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setOppid(String oppid) {
        this.oppid = oppid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIsFirstLogin() {
        return isFirstLogin;
    }

    public void setIsFirstLogin(String isFirstLogin) {
        this.isFirstLogin = isFirstLogin;
    }

    public Date getLastLogonTime() {
        return lastLogonTime;
    }

    public void setLastLogonTime(Date lastLogonTime) {
        this.lastLogonTime = lastLogonTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMenus() {
        return menus;
    }

    public void setMenus(String menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "oppid='" + oppid + '\'' +
                ", username='" + username + '\'' +
                ", identifier='" + identifier + '\'' +
                ", phone='" + phone + '\'' +
                ", isFirstLogin='" + isFirstLogin + '\'' +
                ", lastLogonTime=" + lastLogonTime +
                ", remark='" + remark + '\'' +
                ", menus='" + menus + '\'' +
                '}';
    }
}
