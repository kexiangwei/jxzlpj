package com.mycode.jiaoxueyanjiu.jiaogailunwen.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 教学研究-教改论文
 */
@Getter
@Setter
public class JiaoGaiLunWen {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    //
    private Integer shenHeUserId //获取审核列表参数
            ,userId;
    private String userName
            ,userUnit;

    //逻辑字段
    private String isSubmit //提交状态【已提交 | 未提交】
            ,shenheCode;//审核编号
    private Integer batchNum; //提交批次
    private String status //提交列表审核状态
            ,shenheStatus; //审核列表审核状态

    //业务字段
    private String code
            ,lwTitle
            ,qkName
            ,qkType
            ,dyAuthorCode
            ,dyAuthorName
            ,dyAuthorUnit
            ,txAuthorCode
            ,txAuthorName
            ,txAuthorUnit
//            ,fbTime //发表时间,格式：publishYear年publishIssue期
            ,publishYear; //发表时间：xx年
//            ,publishIssue; //发表时间：xx期

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale="zh", timezone="GMT+8")
    private Date createDate;

}
