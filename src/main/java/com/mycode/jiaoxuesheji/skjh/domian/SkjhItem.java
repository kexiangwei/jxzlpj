package com.mycode.jiaoxuesheji.skjh.domian;

import lombok.Getter;
import lombok.Setter;

/**
 * 教学设计-授课计划条目
 */
@Getter
@Setter
public class SkjhItem {

    private String relationCode; // 关联的业务数据编号
    private Integer batchNum; //业务数据提交批次
    private String code
            ,week //星期
            ,weekNum //周次
            ,date //日期
            ,lessonNum //节次
            ,lessonHour //学时,课程时长
            ,teacher //任课老师
            ,teachChapter  //讲授课程章节
            ,teachAddr; //授课地点
    private String jxDesign //课堂教学设计
            ,szElement; //思政要素


}
