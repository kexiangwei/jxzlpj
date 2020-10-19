package com.mycode.jiaoxuesheji.kcjxssfa.domian;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * 教学设计-课程教学实施方案
 */
@Getter
@Setter
public class Course {

    //业务字段
    private String courseCode //课程编号
            , courseName //课程名称
            , courseType //课程性质
            , stuHour //学时
            , stuScore //学分
            , majorName  //适用专业
            , collegeName;  //开课学院（部）名称
}
