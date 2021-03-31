package com.mycode.jxzlpj.jiaoxuepingjia.thpj.domian;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * 教学评价-查看同行评教
 */
@Getter
@Setter
public class Ckpj {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    private String userId;
    //业务字段
    private String courseCode //课程编号
            ,courseName //课程名称
            ,courseAttr //课程性质
            ,xf //
            ,xs //
            ,majorName //
            ,collegeName; //
    //评审状态
    private Integer isPj; //1已评2未评
    private String templateCode; //评教模板编号

}
