package com.mycode.jiaoxuexiaoguo.kczlfxbg.doman;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

/**
 * 教学效果-课程质量分析报告
 */
@Getter
@Setter
public class Kczlfxbg {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1 //页数，默认为第一页
            ,pageSize=10; //页面尺寸，默认每页10条数据

    private String code //业务编号
            ,courseCode //课程编号
            ,courseName //课程名称
            ,courseAttr //课程性质：【公共必修课/公共选修课/专业必修课（专业基础课或专业核心课）/专业选修课】
            ,xueNian //学年
            ,xueQi //学期
            ,openCollege //开课学院（部）
            ,xiJiaoyanshi //系（教研室）
            ,teachers //授课教师：（填写该课程的所有讲授教师）
            ,teachClass; //授课班级：XX学院XX专业XX年级XX班级（公共选修课无需填写授课班级）

    private String userId
            ,userName
            ,userUnit;
    private Set<String> userGroup;

    //数据录入时间
    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createDate;
}
