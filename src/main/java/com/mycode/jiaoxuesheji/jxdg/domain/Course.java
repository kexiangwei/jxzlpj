package com.mycode.jiaoxuesheji.jxdg.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * @auther kexiangwei
 * @date 2019/10/8
 */

@Getter
@Setter
public class Course {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    //查询字段
    private String shenHeUserId
            ,userId,userName
            ,courseCode,courseName;
    //逻辑字段
    private String isSubmit //提交状态【已提交 | 未提交】
            ,shenheCode;//审核编号
    private Integer batchNum; //提交批次
    private String status //数据状态：【审核中 | 通过 | 退回】
            ,shenheStatus; //审核状态：【已审核 | 待审核 | 退回】

    //业务字段
    private String code
            ,nameZh,nameEn
            ,type
            ,score
            ,stuHour
            ,collegeName;

}
