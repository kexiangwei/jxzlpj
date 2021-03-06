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
//    private String collegeOrDept; //学院或部门

    //业务字段
    private String userId
            ,userName
            ,accountType //账号类型
//            ,maxAuthLevel //最高权限级别
            ,userGroup //用户组
            ,headImg //头像地址
            ,phone;

    @JsonIgnore
    private String password;

    /*//
    private String sex;
    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate; //业务数据录入时间
    private Integer age;*/

    private String userUnit
            ,majorCode,majorName //专业
            ,collegeCode,collegeName; //院系（部门）

}
