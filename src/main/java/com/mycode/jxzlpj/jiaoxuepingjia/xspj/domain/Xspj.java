package com.mycode.jxzlpj.jiaoxuepingjia.xspj.domain;

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
    //
    private Integer isPj; //评教状态【1已评2未评】
    private String templateCode;
    //业务字段
    private String courseCode //课程编号
            ,courseName //课程名称
            ,courseAttr //课程类型
            ,xf //学分
            ,xs //学时
            ,majorName //适用专业
            ,collegeName; //开课学院
//    private String teacherNames; //学生身份登录返回的字段：本课程所有任课教师的姓名
//查询字段
private String accountType //账号类别
        ,userId
        ,userName;
}
