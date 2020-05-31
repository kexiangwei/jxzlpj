package com.mycode.common.teacher.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 双创教育-指导教师信息
 */
@Getter
@Setter
public class Teacher {

    private String relationCode
            ,teacherCode //工号
            ,teacherName //姓名
            ,teacherUnit;  //单位

}
