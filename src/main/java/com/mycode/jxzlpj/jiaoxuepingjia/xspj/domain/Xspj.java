package com.mycode.jxzlpj.jiaoxuepingjia.xspj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycode.common.common.domain.Course;
import lombok.Getter;
import lombok.Setter;

/**
 * 教学评价-学生评教
 */
@Getter
@Setter
public class Xspj extends Course {

    //查询字段
    private String accountType; //账号类别
    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    //
    private Integer isPj; //评教状态【1已评2未评】
    //
    private Long code;
    private String templateCode
            ,transferSelectedDatas;
    private String pjSuggest //评教建议
            ,userId
            ,userName;

}
