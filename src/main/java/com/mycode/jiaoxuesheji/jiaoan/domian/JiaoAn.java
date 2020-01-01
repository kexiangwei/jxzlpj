package com.mycode.jiaoxuesheji.jiaoan.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 教学设计-教案
 * @auther kexiangwei
 * @date 2019/11/13
 */
@Getter
@Setter
public class JiaoAn {

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

    //业务字段
    private String code //编号
            ,courseCode //课程编号
            ,courseName; //课程名称
    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date teachDate; //上课时间
    private String teachNum //节次
            ,teachTopic //课题
            ,teachHour //学时
            ,teachGoal //教学目的及要求
            ,teachContent //教学内容要点
            ,teachKeyAndDifficult //教学重点难点
            ,teachWay //教学方法及教具
            ,teachProcess //教学进程
            ,remark; //备注

    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate; //数据录入时间


}
