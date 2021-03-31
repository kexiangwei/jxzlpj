package com.mycode.jxzlpj.jiaoxuepingjia.thpj.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 教学评价-课程质量评价表对应实体
 */
@Getter
@Setter
public class Thpj {

    private String code
                ,templateCode //使用的模板编号
                ,teacherCode
                ,teacher
                ,teacherCollege
                ,courseCode
                ,courseName
                ,courseAttr
                ,stuClass; //学生班级
    private Integer teachYear
                ,teachMonth
                ,teachDay
                ,teachWeekNum
                ,teachWeek
                ,teachLessonNum;
    private String teachHouse
                ,teachClassroom;
    private Integer stuYingdao
                ,stuShidao
                ,stuChidao
                ,stuQueke;
    private String dpTeacher
                ,dpStudent
                ,dpClassroom;

    private List<Map<String,Object>> thpjItemList;

    private String userId
                ,userName;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDate;


}