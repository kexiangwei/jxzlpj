package com.mycode.jxzlpj.jiaoxueyanjiu.jiaoxuetuandui.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycode.shenheSet.domain.ShenHeObj;
import com.mycode.shenheSet.domain.ZjshItem;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 教学研究-教学团队
 */
@Getter
@Setter
public class JiaoXueTuanDui extends ShenHeObj {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    //用于区分教学团队下的申报或者是年度报告
    private String type;
    //
    private String shenHeUserId //获取审核列表参数
            ,userId;
    private String userName
            ,userUnit;

    //逻辑字段
    private String isSubmit //提交状态【已提交 | 未提交】
            ,shenheCode;//审核编号
    private Integer batchNum; //提交批次
    private String status //数据状态：【待审核 | 审核中 | 通过 | 未通过 | 退回】
            ,shenheStatus //审核状态：【待审核 | 审核中 | 通过 | 未通过 | 退回】
            ,shenheStatusFirst //审核状态-初审：【已审核 | 未审核 】
            ,shenheStatusFinal; //审核状态-终审：【已审核 | 未审核 | 待审核】

    private Integer isZjshAccount
            ,isZjshAll; //专家是否已全部审核完毕【1是0否】
    private List<ZjshItem> zjshItemList; //专家审核信息列表

    //业务字段
    private String code //业务编号
            ,teamName //团队名称
            ,declareOrReport; //申报书，年度报告通用字段
    private String reportResult; //年度报告考核结果
    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registDate //团队建立时间
            ,createDate; //数据提交时间

}
