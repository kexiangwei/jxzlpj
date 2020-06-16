package com.mycode.system.user.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class User {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    private String collegeOrDept //学院或部门
            ,userGroup; //用户组
    //业务字段
    private String userId
            ,userName
            ,accountType //账号类型
            ,headImg //头像地址
            ,phone;
    @JsonIgnore
    private String password;
    //
    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate; //业务数据录入时间
    private String sex;
    private String eduDegree //老师-受教育程度
            ,title; //老师-职称
    private String classCode,className //学生-班级
            ,grade //学生-年级
            ,majorCode,majorName //专业
            ,collegeCode,collegeName; //院系（部门）

}
