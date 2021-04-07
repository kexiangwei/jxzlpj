package com.mycode.system.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class User {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    //查询字段
//    private String collegeOrDept; //学院或部门

    //sys_user
    private String userId;
    private String userName;
    @JsonIgnore
    private String password;
    private String accountType;
    private String headImg;
    //
        private String maxAuthLevel; //最高权限级别
        private String userGroup;//用户组
    //
    private String sex;
    private Integer age;
    private String xyCode  //院系（部门）
            ,xyName;
    private String zyCode  //专业
            ,zyName;
    //教师字段
    private String userUnit; //单位
    private String title; //职称
    //学生字段
    private String nj; //年级
    private String bj; //班级

}
