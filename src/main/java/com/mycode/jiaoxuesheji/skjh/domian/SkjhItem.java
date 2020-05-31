package com.mycode.jiaoxuesheji.skjh.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 教学设计-授课计划条目
 */
@Getter
@Setter
public class SkjhItem extends Skjh{
    //
    private String relationCode; // 关联的业务数据编号
    private String itemCode
            ,week //星期
            ,weekNum //周次
            ,lessonNum //节次
            ,lessonHour //学时,课程时长
            ,teacher //任课老师
            ,teachChapter  //讲授课程章节
            ,teachAddr; //授课地点
    private String jxDesign //课堂教学设计
            ,szElement; //思政要素

    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dates; //日期
}
