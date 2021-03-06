package com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain;

import com.mycode.common.shenheSet.domain.ShenHeObj;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JiaoGaiXiangMuSchool extends ShenHeObj {

    private String code
            //一、项目基本信息
            ,xmNum //项目编号
            ,xmName //项目名称
            ,xmType //项目类型：下拉选择框（重点、一般）
        //二、项目主持人信息
            ,leader //项目主持人
            ,leaderId //项目主持人工号
            ,title //职称：下拉选择框（教授、副教授、讲师、研究员、副研究员、助理研究员等）
            ,collegeOrDept //学院或部门
            ,major //专业：提示若是职能部门，可以不填写。
        //
            ,mainTeachWork //主要教学工作简历
            ,mainTeachAchievement //主要教育教学研究领域及成果
        //四、项目的研究背景与研究基础
            ,currentAndBackground //现状与背景分析（包括已有研究实践基础）
            ,questionAndTarget //研究内容、目标、要解决的问题和主要特色
        //五、项目的预期效果、具体成果和进度安排
            ,expectAndResult //预期效果与具体成果
            ,planAndProcess; //进度安排

    private List<Member> memberList; //主要成员情况
    private List<FundBudget> fundBudgetList; //经费预算

}
