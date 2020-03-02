package com.mycode.shaungchuangjiaoyu.zdxscjcyxm.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 双创教育-指导学生参加创业项目
 */
@Getter
@Setter
public class Project {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;

    //
    private Integer shenHeUserId //获取审核列表参数
            ,userId; //指导教师信息-工号
    private String userName; //指导教师信息-姓名


    //逻辑字段
    private String isSubmit //提交状态【已提交 | 未提交】
            ,shenheCode;//审核编号
    private Integer batchNum; //提交批次
    private String status //数据状态：【待审核 | 审核中 | 通过 | 未通过 | 退回】
            ,shenheStatus; //审核状态：【已审核 | 未审核】

    //业务字段
    private String college,collegeName //在校本科学生信息-学院
            ,major,majorName  //在校本科学生信息-专业
            ,stuCode //在校本科学生信息-学号
            ,stuName; //在校本科学生信息-姓名
    private String code //业务数据编号
            ,projectName //项目名称
            ,level1 //项目级别，一级下拉框【国家级、省部级、校级】
            ,level2 //项目级别，二级下拉框【特等奖、一等奖（金奖）、二等奖（银奖）、三等奖（铜奖）、优秀奖】
            ,category; //项目类别【创新、创业】
    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date projectDate; //项目立项时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDate; //业务数据录入时间

}