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
    private Integer isTxbg //是否填写报告1是2否
            ,isMineCourse //是否我的课程
            ,isThpjRequest = 2; //是否同行评教转发过来的请求，默认不是
    private String bgCode; //若已填写则把报告编号返回
    private Integer isSubmit; //是否提交，1是2否

    private String code //业务编号
                ,courseCode //课程编号
                ,courseName //课程名称
                ,courseAttr //课程性质：【公共必修课/公共选修课/专业必修课（专业基础课或专业核心课）/专业选修课】
                ,xn //学年
                ,xq //学期
                ,xyName //开课学院（部）
                ,zyName //系（教研室）
                ,skjsAll //授课教师：填写该课程的所有讲授教师
                ,skbjAll; //授课班级：公共选修课无需填写授课班级

    //二、学生学习达成度分析
        //（一）考核情况分析
    private String b1_1_1
            ,b1_1_2
            ,b1_1_3;
    private Double b1_1_4_1
            ,b1_1_4_2;
    private String b1_1_5;
    private Integer b1_1_6_1,b1_1_6_2,b1_1_6_3,b1_1_6_4,b1_1_6_5,b1_1_6_6,b1_1_6_7
            ,b1_1_7_1,b1_1_7_2,b1_1_7_3,b1_1_7_4,b1_1_7_5,b1_1_7_6,b1_1_7_7
            ,b1_1_8
            ,b1_1_9;
    private String b1_1_10;
    private Integer b1_2_1_1,b1_2_1_2,b1_2_1_3,b1_2_1_4,b1_2_1_5,b1_2_1_6,b1_2_1_7
            ,b1_2_2_1,b1_2_2_2,b1_2_2_3,b1_2_2_4,b1_2_2_5,b1_2_2_6,b1_2_2_7
            ,b1_2_3
            ,b1_2_4;
    private String b1_2_5;
    private Integer b1_3_1_1,b1_3_1_2,b1_3_1_3,b1_3_1_4,b1_3_1_5,b1_3_1_6,b1_3_1_7
            ,b1_3_2_1,b1_3_2_2,b1_3_2_3,b1_3_2_4,b1_3_2_5,b1_3_2_6,b1_3_2_7
            ,b1_3_3
            ,b1_3_4;
    private String b1_3_5;
    private Integer b1_4_1_1,b1_4_1_2,b1_4_1_3,b1_4_1_4,b1_4_1_5,b1_4_1_6,b1_4_1_7
            ,b1_4_2_1,b1_4_2_2,b1_4_2_3,b1_4_2_4,b1_4_2_5,b1_4_2_6,b1_4_2_7
            ,b1_4_3
            ,b1_4_4;
    private String b1_4_5;
        //（二）学风评价
    private String b2_1,b2_2,b2_3,b2_4,b2_5,b2_6,b2;

    //三、教师教学实施计划执行与效果（自评）
    private Integer c1_1,c1_2,c1_3,c1_4,c1_5,c1_6,c1_7,c1_8,c1_9,c1; //（一）自我评分
    private String c2; //（二）创新与特色

    //四、存在的问题分析及改进措施
    private String d1 //（一）存在的问题
        ,d2; //（二）改进措施

    private String accountType
            ,userId
            ,userName;
    @JsonIgnore
    private Date createDate;
}


