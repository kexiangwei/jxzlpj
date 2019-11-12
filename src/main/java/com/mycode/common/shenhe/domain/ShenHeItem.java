package com.mycode.common.shenhe.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @auther kexiangwei
 * @date 2019/7/13
 */
@Getter
@Setter
public class ShenHeItem {

    private String nodeCode
            ,nodeName;
    private String relationCode; // 关联的信息编号
    private Integer batchNum;
    private String status // 审核状态【通过 | 退回】
                ,opinion; // 审核意见
    private Integer userId;
    private String userName;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createDate;

}
