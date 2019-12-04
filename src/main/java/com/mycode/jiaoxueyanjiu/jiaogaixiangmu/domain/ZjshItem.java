package com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @auther kexiangwei
 * @date 2019/7/13
 */
@Getter
@Setter
public class ZjshItem {

    private String xmCode; // 关联的信息编号
    private Integer batchNum;
    private String status // 审核状态【通过 | 退回】
                ,opinion; // 审核意见
    private Integer userId;
    private String userName;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale="zh", timezone="GMT+8")
    private Date createDate;

}
