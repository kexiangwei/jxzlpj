package com.mycode.jiaoxuepingjia.pjset.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 教学评价-评教设置
 */
@Getter
@Setter
public class PjSet {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;

    //业务字段
    private String code
            ,templateCode
            ,templateType
            ,templateName;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startDate
            ,endDate
            ,createDate;

}
