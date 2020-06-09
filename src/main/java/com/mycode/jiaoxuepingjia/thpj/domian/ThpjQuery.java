package com.mycode.jiaoxuepingjia.thpj.domian;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * 教学评价-同行评教查询实体
 */
@Getter
@Setter
public class ThpjQuery {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;

    //业务字段
    private String courseCode //课程编号
            ,courseName //课程名称
            ,courseType //课程性质
            ,teacher //任课教师姓名
            ,teacherAge //教师年龄
            ,teacherTitle //教师职称
            ,teacherMajor //教师所在专业
            ,teacherCollege //教师所在学院
            ,teachDate //上课时间
            ,teachAddr; //上课地点

}
