package com.mycode.jxzlpj.jiaoxuepingjia.thpj.domian;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * 教学评价-同行评教查询实体
 */
@Getter
@Setter
public class ThpjQuery {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    //评审状态
    private String userId;
    private Integer isPj; //1已评2未评
    private String pjCode; //评教编号
    private Integer isTop //本次评分是否90分及以上，1是2否
            ,isSubmit; //是否提交
    //业务字段
    private String courseCode //课程编号
            ,courseName //课程名称
            ,courseAttr //课程性质
            ,teacherCode //任课教师编号
            ,teacher //任课教师姓名
            ,teacherAge //教师年龄
            ,teacherTitle //教师职称
            ,teacherMajor //教师所在专业
            ,teacherCollege //教师所在学院
            ,teachDate //上课时间
            ,teachAddr; //上课地点
}
