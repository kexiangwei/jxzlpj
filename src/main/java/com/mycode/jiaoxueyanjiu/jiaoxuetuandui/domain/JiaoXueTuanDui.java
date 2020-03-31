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
 * 教学研究-教学团队
 */
@Getter
@Setter
public class JiaoXueTuanDui {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    //
    private Integer isPsAccount; //是否为评审账号（即拥有评审角色的账号）
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

    //业务字段
    private String code //业务编号
            ,teamName //团队名称
            ,teamLeader //团队负责人姓名
            ,teamLeaderId //团队负责人工号
            ,teamLeaderUnit; //团队负责人单位
    //
    private String sbs //申报书
            ,middleReport //中期报告
            ,middleResult //中期考核结果,默认【未审核】，提交后 -> 根据评委评分结果 -> 管理员确认后，结果更新为【已审核】
            ,finalReport //总结报告
            ,finalResult; //最终考核结果,默认【未审核】，提交后 -> 根据评委评分结果 -> 管理员确认后，结果更新为【已审核】
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registDate //团队建立时间
            ,createDate; //数据提交时间

}
