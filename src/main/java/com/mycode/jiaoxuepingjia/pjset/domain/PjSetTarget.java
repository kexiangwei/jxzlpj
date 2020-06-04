package com.mycode.jiaoxuepingjia.pjset.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 教学评价-评教设置-指标
 */
@Getter
@Setter
public class PjSetTarget {

    private String targetCode
            ,targetType
            ,targetName
            ,targetContent;
    private Integer targetScore;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDate;

}
