package com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycode.shaungchuangjiaoyu.zdxscjcyxm.domian.Project;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class JiaoGaiXiangMuForCity {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    //
    private String shenHeUserId //获取审核列表参数
            ,userId;
    private String userName
            ,userUnit;
    //逻辑字段
    private String isSubmit //提交状态【已提交 | 未提交】
            ,shenheCode;//审核编号
    private Integer batchNum; //提交批次
    private String status //数据状态：【审核中 | 通过 | 退回】
            ,shenheStatus //审核状态：【已审核 | 待审核 | 退回】
            ,shenheStatusFirst //审核状态-初审：【已审核 | 待审核 | 退回】
            ,shenheStatusFinal; //审核状态-终审：【已审核 | 待审核 | 退回 | 待审核（未初审）】
    private Integer isJwcGly //是否教务处管理员
            ,isZjshAccount //是否专家评审账号
            ,isZjshAll; //校外专家是否已审核（全部）
    private List<ZjshItem> zjshItemList;

    private String code
            , projectName //项目名称
            , projectNum //立项编号
            , projectLevel; //项目级别（重点，面上，自筹经费）
    private Double fund; //经费
    private Integer memberNum; //参数教师数
    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date projectStartTime //立项时间
            , projectEndTime //结题时间
            , createDate;

}
