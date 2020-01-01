package com.mycode.jiaoxuesheji.skjh.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 教学设计-授课计划
 * @auther kexiangwei
 * @date 2019/12/31
 */
@Getter
@Setter
public class Skjh {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    //
    private Integer shenHeUserId //获取审核列表参数
            ,userId;
    private String userName;
    //逻辑字段
    private String isSubmit //提交状态【已提交 | 未提交】
            ,shenheCode;//审核编号
    private Integer batchNum; //提交批次
    private String status //数据状态：【审核中 | 通过 | 退回】
            ,shenheStatus; //审核状态：【已审核 | 待审核 | 退回】
    //业务字段code,stuYear,stuTerm,college,major,courseCode,courseName,teachClass,stuNum,totalHours,theoryHours,testHours,days,createDate
    private String code //业务编号
            ,stuYear //学年
            ,stuTerm //学期
            ,college //学院
            ,major //专业
            ,courseCode //课程编号
            ,courseName //课程名称
            ,teachClass //授课班级
            ,stuNum; //学生人数
    private Integer totalHours //总学时
            ,theoryHours //理论学时
            ,testHours //实验学时
            ,days; //实习天数
    private List<SkjhItem> itemList;
    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate; //业务数据录入时间

}
