package com.scistor.develop.data.entity;

import com.scistor.develop.dao.ParentDao;

import java.util.Date;

/**
 * 对接进度详情
 */
public class ProjectTeamDirectoryDetails extends ParentDao {

    private Long id;

    private Date createrTime;

    private Date updateTime;

    private String deleteFlag;

    private Integer numberDocking;

    private String dockingDetails;

    private Long ptdId;

    public Long getPtdId() {
        return ptdId;
    }

    public void setPtdId(Long ptdId) {
        this.ptdId = ptdId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreaterTime() {
        return createrTime;
    }

    public void setCreaterTime(Date createrTime) {
        this.createrTime = createrTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getNumberDocking() {
        return numberDocking;
    }

    public void setNumberDocking(Integer numberDocking) {
        this.numberDocking = numberDocking;
    }

    public String getDockingDetails() {
        return dockingDetails;
    }

    public void setDockingDetails(String dockingDetails) {
        this.dockingDetails = dockingDetails;
    }
}
