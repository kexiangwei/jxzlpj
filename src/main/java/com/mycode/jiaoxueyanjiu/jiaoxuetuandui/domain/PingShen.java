package com.mycode.jiaoxueyanjiu.jiaoxuetuandui.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 教学研究-教学团队-评审
 */
@Getter
@Setter
public class PingShen {

    private String relationCode; //业务编号
    Integer batchNum; //提交批次
    //业务字段
    private String targetTeamBuildingPlan  //团队建设规划
            ,targetTeamCompose //团队组成
            ,targetTeamLeader //团队负责人
            ,targetTeachingWork //教学工作
            ,targetTeachingResearch//教学研究
            ,targetInnovationAndEntrepre  //创新创业
            ,targetTeacherTraining; //教师培养
    private Double totalScore; //总分
    private String pingshenOpinion; //评审意见
    //
    private String userId; //评委工号
    private String userName; //评委姓名
    //
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale="zh", timezone="GMT+8")
    private Date createDate; //数据提交时间

}
