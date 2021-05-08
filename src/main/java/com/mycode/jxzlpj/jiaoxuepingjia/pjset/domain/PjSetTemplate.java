package com.mycode.jxzlpj.jiaoxuepingjia.pjset.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 教学评价-评教设置-模板
 */
@Getter
@Setter
public class PjSetTemplate {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    //
    private Integer isActive; //模板是否启用（即是否可执行编辑&删除操作），取值：【1是，2否】
    private Integer isUse; //
    //
    private String userId;
    private Integer isPj; //是否评教，取值：【1是，2否】

    private String templateCode
            ,templateType
            ,templateName;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startDate
            ,endDate
            ,createDate;

}
