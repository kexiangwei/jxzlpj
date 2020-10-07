package com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mycode.shenhe.domain.ShenHeObj;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class JiaoGaiXiangMuProvince extends ShenHeObj {

    private String code
            , projectName //项目名称
            , projectNum //立项编号
            , projectLevel; //项目级别（重点，面上，自筹经费）
    private Double fund; //经费
    private Integer memberNum; //参与教师人数
    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date projectStartTime //立项时间
            , projectEndTime; //结题时间

}
