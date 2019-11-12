package com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 教改项目
 * @auther kexiangwei
 * @date 2019/8/19
 */
@Getter
@Setter
public class JiaoGaiXiangMu {

    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;

    private String code
            ,xmType //项目类型【重点、一般】
            ,xmName; //项目名称
    private Double sqMoney;
    private String userId
            ,userName
            ,title //职称
            ,collegeDept //学院（部门）
            ,major //专业
            ,mainTeachWork //主要教学工作
            ,mainTeachAchievement //主要教学成果
            ,currentAndBackground
            ,questionAndTarget
            ,expectAndResult
            ,planAndProcess
            ,budgetMoney;
    private String isSubmit
            ,shenheCode
            ,status;
    private Integer batchNum;

    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createDate;
}
