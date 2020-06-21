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
 */
@Getter
@Setter
public class Skjh {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    //
    private String shenHeUserId //获取审核列表参数
            ,userId;
    private String userName;
    //逻辑字段
    private String isSubmit //提交状态【已提交 | 未提交】
            ,shenheCode;//审核编号
    private Integer batchNum; //提交批次
    private String status //数据状态：【审核中 | 通过 | 退回】
            ,shenheStatus; //审核状态：【已审核 | 待审核 | 退回】

    //业务字段
    private String code //业务编号
//            ,stuYear //学年
//            ,stuTerm //学期
            ,college //开课学院（部）
//            ,major //适用专业
            ,courseCode //课程编号
            ,courseName //课程名称
            ,courseType //课程类别，可选值【公共必修课/公共选修课/专业必修课（专业基础课）/专业必修课（专业核心课）/专业选修课】
            ,stuHour //学时
            ,stuScore //学分
            ,mainTeacher //主讲教师
            ,teachClass; //授课班级
    //
    /*private List<SkjhItem> itemList;*/
    //
    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate; //业务数据录入时间

}
