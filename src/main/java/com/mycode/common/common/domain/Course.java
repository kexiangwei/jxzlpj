package com.mycode.common.common.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 教学质量评价-课程信息
 */
@Getter
@Setter
public class Course {

    //业务字段
    private String courseCode //课程编号
            , courseName //课程名称
            , courseAttr //课程性质
            , stuHour //学时
            , stuScore //学分
            , majorName  //适用专业
            , collegeName;  //开课学院（部）名称
}
