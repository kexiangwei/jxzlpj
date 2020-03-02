package com.mycode.shaungchuangjiaoyu;

import lombok.Getter;
import lombok.Setter;

/**
 * 参赛学生信息
 */
@Getter
@Setter
public class Stu {

    private String relationCode
            ,stuCode //参赛学生信息-学号
            ,stuName //参赛学生信息-姓名
            ,college //参赛学生信息-学院
            ,major;  //参赛学生信息-专业

}
