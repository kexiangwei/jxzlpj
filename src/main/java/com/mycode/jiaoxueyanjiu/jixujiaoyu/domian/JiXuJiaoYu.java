package com.mycode.jiaoxueyanjiu.jixujiaoyu.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 教学研究-继续教育
 * @auther kexiangwei
 * @date 2019/7/13
 */
@Getter
@Setter
public class JiXuJiaoYu {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    //业务字段
    private String code,peixunName,peixunStyle,peixunContent,peixunAddress,peixunDept;
    private Integer peixunClassHour;
    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date peixunStartTime,peixunEndTime,createDate;
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



}
