package com.mycode.jiaoxuexiaoguo.kcxj.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class Kcxj {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    //
    private Integer shenHeUserId //获取审核数据列表参数
            ,userId; //获取业务数据列表参数
    private String userName;

    private String isSubmit //提交状态【已提交 | 未提交】
            ,shenheCode; //审核编号
    private Integer batchNum; //提交批次
    private String status //数据状态：【审核中 | 通过 | 退回】
            ,shenheStatus; //审核状态：【已审核 | 待审核 | 退回】

    //业务字段
    private String code //业务数据编号
            , college  //开课学院
            , major //适用专业
            , courseCode //课程编号
            , courseName; //课程名称
    //
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate; //数据录入时间
}
