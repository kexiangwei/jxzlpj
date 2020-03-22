package com.mycode.shaungchuangjiaoyu.student.domian;

import lombok.Getter;
import lombok.Setter;

/**
 * 双创教育-学生信息
 */
@Getter
@Setter
public class Student {

    private String relationCode //关联的业务数据编号
            ,college //学院
            ,major   //专业
            ,studentCode //学号
            ,studentName //姓名
            ,sorted; //排序

}
