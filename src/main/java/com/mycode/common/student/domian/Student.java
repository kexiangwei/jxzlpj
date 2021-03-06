package com.mycode.common.student.domian;

import lombok.Getter;
import lombok.Setter;

/**
 * 双创教育-学生信息
 */
@Getter
@Setter
public class Student {

    private String relationCode //关联的业务数据编号
            ,studentCode //学号
            ,studentName //姓名
            ,college //学院
            ,major   //专业
            ,sorted; //排序

}
