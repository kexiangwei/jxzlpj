package com.mycode.shenheSet.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ShenHe {

    //
    private String shenheCode
            ,relationCode; // 关联的信息编号
    private Integer batchNum;
    private String status; // 审核意见
    private String userId;
    private String userName;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createDate;
    //
    private List<ShenHeItem> shenHeItemList = new ArrayList<>();

}
