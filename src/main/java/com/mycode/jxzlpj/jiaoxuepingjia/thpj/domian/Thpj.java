package com.mycode.jxzlpj.jiaoxuepingjia.thpj.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 教学评价-督导同行评教对应实体
 */
@Getter
@Setter
public class Thpj {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    //评审状态
    private Integer isPj; //是否评教，1是2否
    private String pjCode; //评教编号
    private Integer isSubmit //是否提交，1是2否
            ,isGte90; //本次评分是否90分及以上，1是2否

    private String code
            ,xn
            ,xq
                ,courseCode //课程编号
                ,courseName //课程名称
                ,courseAttr //课程性质
            ,courseXy //开课学院
            ,courseZy //适用专业
                ,skjsCode //任课教师编号
                ,skjsName //任课教师姓名
            ,age //教师年龄
            ,title //教师职称
                ,skjsXy //教师所在学院
                ,skjsZy //教师所在专业
                ,skSj //上课时间
                ,skDd //上课地点
                ,skBj; //授课班级

    private Integer teachYear
                ,teachMonth
                ,teachDay
                ,teachWeekNum
                ,teachLessonNum;
    private String teachWeek;
    private String teachHouse
                ,teachClassroom;

    private Integer stuYd
                ,stuSd
                ,stuCd
                ,stuQk;

    private String templateCode; //使用的模板编号
    private List<Map<String,Object>> thpjItemList;

    private String teacherDp1
                ,teacherDp2
                ,studentDp1
                ,studentDp2
                ,classroomDp;

    private String userId
                ,userName;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDate;

}
