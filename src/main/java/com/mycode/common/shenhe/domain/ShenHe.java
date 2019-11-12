package com.mycode.common.shenhe.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @auther kexiangwei
 * @date 2019/7/13
 */
@Getter
@Setter
public class ShenHe {

    private String shenheCode
            ,relationCode; // 关联的信息编号
    private Integer batchNum;
    private String status; // 审核意见
    private Long userId;
    private String userName;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createDate;
    //
    private List<ShenHeItem> shenHeItemList = new ArrayList<>();
}
