package com.mycode.jiaoxuepingjia.xspj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * @auther kexiangwei
 * @date 2019/10/8
 */

@Getter
@Setter
public class Course {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    //查询字段
    private String userId;

    //业务字段
    private String code
            ,nameZh,nameEn
            ,type
            ,score
            ,stuHour
            ,collegeName;

}
