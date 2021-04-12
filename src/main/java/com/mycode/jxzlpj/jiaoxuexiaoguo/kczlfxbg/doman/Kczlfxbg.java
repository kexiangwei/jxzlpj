package com.mycode.jxzlpj.jiaoxuexiaoguo.kczlfxbg.doman;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * 教学效果-课程质量分析报告
 */
@Data
public class Kczlfxbg {

    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;

    private String code //业务编号
            ,courseCode //课程编号
            ,courseName //课程名称
            ,courseAttr //课程性质：【公共必修课/公共选修课/专业必修课（专业基础课或专业核心课）/专业选修课】
            ,xn //学年
            ,xq //学期
            ,xyName //开课学院（部）
            ,zyName //系（教研室）
            ,skjs //授课教师：填写该课程的所有讲授教师
            ,skbj; //授课班级：公共选修课无需填写授课班级

    private String userId
            ,userName;
    @JsonIgnore
    private Date createDate;
}
