package com.mycode.common.shenheSet.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class ShenHeItem {

    //
    private Integer isZjshAccount; //是否校外专家审核账号
    //
    private String viewName; //待审核模块视图名称
    //
    private String nodeCode
            ,nodeName;
    //
    private String relationCode; // 关联的信息编号
    private Integer batchNum;
    private String shenheType; //审核类别：【初审，终审】
    private String status // 审核状态【通过 | 未通过 | 退回】
                ,opinion; // 审核意见
    private String userId;
    private String userName;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createDate;

}
