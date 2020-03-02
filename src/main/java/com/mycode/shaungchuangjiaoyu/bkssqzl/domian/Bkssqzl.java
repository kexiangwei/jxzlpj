package com.mycode.shaungchuangjiaoyu.bkssqzl.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycode.shaungchuangjiaoyu.Stu;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 双创教育-本科生申请专利
 */
@Getter
@Setter
public class Bkssqzl {

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
    private String code //业务数据编号
            ,name //名称
            ,type //类型【发明专利、实用新型专利、外观设计专利、软件著作权】
            ,authNum //授权号
            ,owner //专利权（著作权）人
            ,isFirstInventor; //学生是否为第一发明人：【是|否】
    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date approvalTime; //获批时间

    private String college,collegeName //在校本科学生信息-学院
            ,major,majorName  //在校本科学生信息-专业
            ,stuCode //在校本科学生信息-学号
            ,stuName; //在校本科学生信息-姓名
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDate; //业务数据录入时间

}