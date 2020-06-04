package com.mycode.jiaoxuepingjia.xspj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * 教学评价-学生评教
 */
@Getter
@Setter
public class Xspj {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    //查询字段
    private String userId
            ,userName;

    //业务字段
    private String courseCode //课程编号
            ,courseName //课程名称
            ,teacherNames; //本课程所有任课教师的姓名
    private Integer isPj; //评教状态【1已评2未评】
}
