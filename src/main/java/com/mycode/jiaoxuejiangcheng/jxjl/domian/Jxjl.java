package com.mycode.jiaoxuejiangcheng.jxjl.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 教学奖惩-教学奖励
 */
@Getter
@Setter
public class Jxjl {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;

    //
    private Integer shenHeUserId //获取审核列表参数
            ,userId; //获取信息列表|查询参数
    private String userName
            ,userUnit;

    //逻辑字段
    private String isSubmit //提交状态【已提交 | 未提交】
            ,shenheCode;//审核编号
    private Integer batchNum; //提交批次
    private String status //数据状态：【待审核 | 审核中 | 通过 | 未通过 | 退回】
            ,shenheStatus; //审核状态：【已审核 | 未审核】

    //业务字段 code,objname,personrank,unitrank,level,levelprize,prizetime,certificatecode,grantunit,userId,userName,createdate
    private String code //业务数据编号
            ,objName //奖项名称
            ,level1 //奖项级别，一级下拉框【国家级|北京市级|校级】
            ,level2 //获得奖项，二级下拉框[一等奖|二等奖|三等奖|其他]
            ,grantUnit //证书授予机构
            ,datetimeYear,datetimeYearStart,datetimeYearEnd; //获奖日期(年)

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDate; //业务数据录入时间

}
