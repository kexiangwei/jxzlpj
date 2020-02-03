package com.mycode.jiaoxuejiangcheng.jyjxcgj.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 教学奖惩-教育教学成果奖
 */
@Getter
@Setter
public class Jyjxcgj {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;

    //
    private Integer shenHeUserId //获取审核列表参数
            ,userId; //获取信息列表|查询参数
    private String userName;

    //逻辑字段
    private String isSubmit //提交状态【已提交 | 未提交】
            ,shenheCode;//审核编号
    private Integer batchNum; //提交批次
    private String status //数据状态：【审核中 | 通过 | 未通过 | 退回】
            ,shenheStatus; //审核状态：【已审核 | 待审核 | 退回】

    //业务字段 code,objname,personrank,unitrank,level,levelprize,prizetime,certificatecode,grantunit,userId,userName,createdate
    private String code //业务数据编号
            ,objName //成果名称
            ,personRank //本人排名
            ,unitRank //完成单位排名
            ,level_1 //级别，一级下拉框【国家级|北京市级|校级】
            ,level_2 //奖项，二级下拉框[特等奖|一等奖|二等奖|三等奖]
            ,certificateCode //获奖证书编号
            ,grantUnit; //授予单位
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date prizeTime //获奖时间
            ,createDate; //业务数据录入时间

}
