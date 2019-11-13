package com.mycode.jiaoxueyanjiu.jiaocaijianshe.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 教学研究-教材建设
 * @auther kexiangwei
 * @date 2019/11/13
 */
@Getter
@Setter
public class JiaoCaiJianShe {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    //业务字段
    private String code //编号
            ,name //名称
            ,category //类别：教材，专著（专著、译著、辞书）
            ,participationType //参与形式：主编、副主编、参编
            ,isbn
            ,publishers //出版社
            ,publishingTime //出版时间
            ,selected //教材入选情况：国家规划教材、省部级规划教材、国家级精品教材、省部级精品教材、其他
            ,selectedTime; //入选时间
    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    private Date createDate;
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



}
