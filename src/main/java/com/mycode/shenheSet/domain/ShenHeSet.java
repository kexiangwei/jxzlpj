package com.mycode.shenheSet.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 审核流程设置
 */
@Getter
@Setter
public class ShenHeSet {

    //查询参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    //
    private String shenheProcess; //审核流程,用于表格显示【老师 > 学院管理员 > 教务处管理员】
    private Integer isDelete;//数据状态，是否可以执行删除操作,可选值【0-不可删除，1可以删除】
    //业务字段
    private Long parentMenuId
            ,menuId;// 业务模块编号
    private String menuName; // 业务模块名称
    private String shenheCode //审核流程编号
            ,shenheName //审核流程名称
            ,shenheDesc; //审核流程描述
    private Integer status; //审核流程当前状态，1-当前在用，0-已禁用
    private Date createDate;

}
