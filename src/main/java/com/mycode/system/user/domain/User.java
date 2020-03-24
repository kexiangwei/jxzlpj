package com.mycode.system.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

/**
 * @auther kexiangwei
 * @date 2019/7/13
 */
@Getter
@Setter
public class User {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    //业务字段
    private String userId,userName,password
            ,accountLevel //账号级别【管理员、老师、学生、其他】
//            ,accountStatus //账号状态【激活|禁用】
            ,headImg //头像地址
            ,phone;
    //
    private String classCode,className //班级
            ,majorCode,majorName //专业
            ,collegeCode,collegeName; //院系（部门）
}
