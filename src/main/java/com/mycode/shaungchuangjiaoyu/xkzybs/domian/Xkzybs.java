package com.mycode.shaungchuangjiaoyu.xkzybs.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 双创教育-学科专业比赛
 */
@Getter
@Setter
public class Xkzybs {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    //表头查询字段
    @JsonIgnore
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date grantDate_start
            ,grantDate_end;
    //
    private String shenHeUserId //获取审核列表参数
            ,userId; //指导教师信息-工号
    private String userName  //指导教师信息-姓名
            ,userUnit;  //指导教师信息-单位

    //逻辑字段
    private String isSubmit //提交状态【已提交 | 未提交】
            ,shenheCode;//审核编号
    private Integer batchNum; //提交批次
    private String status //数据状态：【待审核 | 审核中 | 通过 | 未通过 | 退回】
            ,shenheStatus; //审核状态：【已审核 | 未审核】

    //业务字段
    private String code //业务数据编号
            ,name //赛事名称
            ,level1 //获奖等级，一级下拉框【国家级、省部级、校级】
            ,level2 //获得奖项，二级下拉框【特等奖、一等奖（金奖）、二等奖（银奖）、三等奖（铜奖）、优秀奖】
            ,grantUnit; //证书授予单位
    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date grantDate; //证书授予时间

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDate; //业务数据录入时间

}