package com.mycode.jxzlpj.jiaoxuesheji.kcjxssfa.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 教学设计-课程教学实施方案
 */
@Getter
@Setter
public class Kcjxssfa {

    //分页查询参数
    @JsonIgnore
    private Integer pageIndex=1 //页数，默认为第一页
            ,pageSize=10; //页面尺寸，默认每页10条数据

    //业务字段
    private String code //业务数据编号
            , courseCode //课程编号
            , courseName //课程名称
            , courseAttr //课程性质
            , courseLeader //课程负责人
            , teachClass //授课班级
            , studentNum //学生人数
            , classLocation  //上课地点
            , openCollege;  //开课学院（部）名称
    //
    private String xn
            ,xq;
    private String userId
            ,userName
            ,userUnit;
    //数据录入时间
    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createDate;
}
