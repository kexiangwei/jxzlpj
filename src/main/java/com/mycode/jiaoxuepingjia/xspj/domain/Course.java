package com.mycode.jiaoxuepingjia.xspj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * 教学评价-学生评教-课程
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
    private String userId
            ,userName;

    //业务字段
    private String code //课程编号
            ,nameZh //中文名称
            ,nameEn //英文名称
            ,score //学分
            ,stuHour //学时
            ,type //
            ,collegeName;  //

}
