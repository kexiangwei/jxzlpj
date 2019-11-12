package com.mycode.jiaoxuepingjia.xspj.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @auther kexiangwei
 * @date 2019/10/9
 */
@Getter
@Setter
public class XspjSet {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;

    //业务字段
    private String code
            ,name
        ,templateCode
        ,templateName
            ,remark;
    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate,endDate,createDate,lastModifyDate;

}
