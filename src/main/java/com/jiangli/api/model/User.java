package com.jiangli.api.model;

import java.util.Date;

/**
 * 用户表
* @Description
* @author shisong
* @date 14:49 2018/1/31
* @modifyNote
* @param
* @return
*/
public class User {

    private Long id;//arise后台管理用户id
    private Long userId;//arise用户id
    private Integer isLock;//0:未锁定 1:已锁定 锁定用户不能访问
    private Integer isDelete;//删除标记 0:未删除 1:已删除;
    private Date createTime;//'创建时间'
    private Date updateTime;//'修改时间'

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getIsLock() {
        return isLock;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId=" + userId +
                ", isLock=" + isLock +
                ", isDelete=" + isDelete +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
