package com.mycode.system.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
public class User {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    //
    /*private String classCode,className //班级
            ,majorCode,majorName //专业
            ,collegeCode,collegeName; //院系（部门）*/

    private String collegeOrDept //学院或部门
            ,userGroup; //用户组
    //业务字段
    private String userId
            ,userName
            ,headImg //头像地址
            ,phone;
    @JsonIgnore
    private String password;
}
