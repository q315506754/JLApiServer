package com.jiangli.api.model;

import lombok.Data;

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
@Data
public class User {
    private Long id;//arise后台管理用户id
    private Long userId;//arise用户id
    private Integer isLock;//0:未锁定 1:已锁定 锁定用户不能访问
    private Integer isDelete;//删除标记 0:未删除 1:已删除;
    private Date createTime;//'创建时间'
    private Date updateTime;//'修改时间'
    private String name;//名字

}
