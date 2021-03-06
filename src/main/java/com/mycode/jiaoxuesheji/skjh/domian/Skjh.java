package com.mycode.jiaoxuesheji.skjh.domian;

import com.mycode.common.shenheSet.domain.ShenHeObj;
import lombok.Getter;
import lombok.Setter;

/**
 * 教学设计-授课计划
 */
@Getter
@Setter
public class Skjh extends ShenHeObj {

    //业务字段
    private String code //业务编号
//            ,stuYear //学年
//            ,stuTerm //学期
            ,college //开课学院（部）
//            ,major //适用专业
            ,courseCode //课程编号
            ,courseName //课程名称
            ,courseType //课程类别，可选值【公共必修课/公共选修课/专业必修课（专业基础课）/专业必修课（专业核心课）/专业选修课】
            ,stuHour //学时
            ,stuScore //学分
            ,mainTeacher //主讲教师
            ,teachClass; //授课班级

}
