package com.mycode.shaungchuangjiaoyu.bksfblw.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 双创教育-本科生发表论文
 */
@Getter
@Setter
public class Bksfblw {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;

    //
    private Integer shenHeUserId //获取审核列表参数
            ,userId; //指导教师信息-工号
    private String userName
            ,userUnit; //指导教师信息-姓名

    //逻辑字段
    private String isSubmit //提交状态【已提交 | 未提交】
            ,shenheCode;//审核编号
    private Integer batchNum; //提交批次
    private String status //数据状态：【待审核 | 审核中 | 通过 | 未通过 | 退回】
            ,shenheStatus; //审核状态：【已审核 | 未审核】

    //业务字段
    private String code //业务数据编号
            ,lwTitle //论文题目
            ,qkName //期刊名称
            ,publishYear //发表时间：xx年
            ,publishIssue //发表时间：xx期
            ,includStatus; //收录情况【SCI、SSCI、EI、CPCI、A&HCI、CSCD、CSSCI、北大中文核心期刊、其他期刊】

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDate; //业务数据录入时间

}