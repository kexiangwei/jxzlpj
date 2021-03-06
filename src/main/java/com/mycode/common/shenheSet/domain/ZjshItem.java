package com.mycode.common.shenheSet.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ZjshItem {

    private String xmCode; // 关联的业务数据编号
    private Integer batchNum;
    private String status // 审核状态【通过 | 退回】
                ,opinion; // 审核意见
    private String userId;
    private String userName;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale="zh", timezone="GMT+8")
    private Date createDate;

}
