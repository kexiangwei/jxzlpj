package com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class JiaoGaiXiangMu {

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
            ,shenheStatus //审核状态：【已审核 | 待审核 | 退回】
            ,shenheStatusFirst //审核状态-初审：【已审核 | 待审核 | 退回】
            ,shenheStatusFinal; //审核状态-终审：【已审核 | 待审核 | 退回 | 待审核（未初审）】
    private Integer isJwcGly //是否教务处管理员
            ,isZjshAccount //是否校外专家审核账号
            ,isZjshAll; //校外专家是否已审核（全部）
    //
    private List<ZjshItem> zjshItemList;

    //
    private String code
            ,xmNum //项目编号
            ,xmName //项目名称
            ,xmType //项目类型：下拉选择框（重点、一般）

            ,leader //项目主持人
            ,leaderId //项目主持人工号
            ,title //职称：下拉选择框（教授、副教授、讲师、研究员、副研究员、助理研究员等）
            ,collegeOrDept //学院或部门
            ,major //专业：提示若是职能部门，可以不填写。
            ,mainTeachWork //主要教学工作简历
            ,mainTeachAchievement; //主要教育教学研究领域及成果

    private List<Member> memberList; //主要成员情况

    private String currentAndBackground //现状与背景分析（包括已有研究实践基础）
            ,questionAndTarget //研究内容、目标、要解决的问题和主要特色
            ,expectAndResult //预期效果与具体成果
            ,planAndProcess; //进度安排

    private List<FundBudget> fundBudgetList; //经费预算

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale="zh", timezone="GMT+8")
    private Date createDate;

}
