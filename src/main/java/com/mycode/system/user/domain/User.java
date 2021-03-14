package com.mycode.system.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=20;
    //
    private String collegeOrDept; //学院或部门

    //业务字段
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
    private String userUnit;
    private String title;
    private String classes;
    private String majorCode
            ,majorName; //专业
    private String collegeCode
            ,collegeName; //院系（部门）

}
