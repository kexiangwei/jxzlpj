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
            , xs //学时
            , xf //学分
            , xn //学年
            , xq //学期
            , xyCode //开课学院（部）编号
            , xyName //开课学院（部）名称
            ,zyCode //专业编号
            ,zyName //专业名称
            ,skjsCode //授课教师编号
            ,skjsName //授课教师姓名
            ,skSj //授课时间
            ,skDd //授课地点
            ,skBj //授课班级
            ,xsrs; //学生人数
}
