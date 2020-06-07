package com.mycode.jiaoxuepingjia.pjset.domain;

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

    private String templateCode
            ,templateType
            ,templateName
            ,templateDesc;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    //
    private Integer isExec; //模板是否启用（即是否可执行编辑&删除操作），取值：【1是，2否】
}
