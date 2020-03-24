package com.mycode.jiaoxuejiangcheng.jxsg.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 教学奖惩-教学事故
 */
@Getter
@Setter
public class Jxsg {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    private String userId
            ,userName
            ,isAdmin; //是否教务处管理员

    //业务字段
    private String code //业务数据编号
            ,teacherCollege,collegeName //教师信息-学院
            ,teacherMajor,majorName //教师信息-专业
            ,teacherId //教师信息-工号
            ,teacherName //教师信息-姓名
            ,teacherUnit //教师信息-姓名
            ,event //事件
            ,eventLevel; //事故认定级别[重大事故、一般事故、过失、负面清单]
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date happenTime,datetimeStart,datetimeEnd //事故认定时间
            ,createTime; //业务数据录入时间

}
