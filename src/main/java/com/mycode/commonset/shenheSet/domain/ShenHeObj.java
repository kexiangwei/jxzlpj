package com.mycode.commonset.shenheSet.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 实例对象顶级父类
 */
@Getter
@Setter
public class ShenHeObj {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1 //页数，默认为第一页
            ,pageSize=10; //页面尺寸，默认每页10条数据

    //查询参数
    private String code; //业务编号
    private String shenHeUserId //获取审核列表参数
            ,userId;
    private String userName
            ,userUnit;

    //逻辑字段
    private String isSubmit //提交状态【已提交 | 未提交】
                ,shenheCode;//审核编号
    private Integer batchNum; //提交批次
    private String status //数据状态：【审核中 | 通过 | 退回】
                ,shenheStatus; //审核状态：【已审核 | 待审核 | 退回】
    private String shenheStatusFirst //审核状态-初审：【已审核 | 待审核 | 退回】
            ,shenheStatusFinal; //审核状态-终审：【已审核 | 待审核 | 退回 | 待审核（未初审）】
    private Integer isJwcGly //是否教务处管理员
            ,isZjshAccount //是否校外专家审核账号
            ,isZjshAll; //校外专家是否已审核（全部）
    private List<ZjshItem> zjshItemList;

    //数据录入时间
    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createDate;
}
