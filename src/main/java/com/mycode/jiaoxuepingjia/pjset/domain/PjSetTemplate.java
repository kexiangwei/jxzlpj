package com.mycode.jiaoxuepingjia.pjset.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 教学评价-评教设置-模板
 * @auther kexiangwei
 * @date 2019/10/8
 */

@Getter
@Setter
public class PjSetTemplate {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;

    private String templateCode
            ,templateName
            ,templateDesc;
    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createDate;

}
