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
    //
    private Integer isBindTemplate; //是否绑定模板（即是否可执行编辑&删除操作），取值：【1已绑定，2未绑定】
    private String templateCode;
    //
    private String targetCode
            ,targetType
            ,targetName
            ,targetContent;
    private Integer targetScore;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDate;

}
