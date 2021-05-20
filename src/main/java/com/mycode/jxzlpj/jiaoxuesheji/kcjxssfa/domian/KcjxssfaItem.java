package com.mycode.jxzlpj.jiaoxuesheji.kcjxssfa.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 教学设计-课程教学实施方案
 */
@Getter
@Setter
public class KcjxssfaItem extends Kcjxssfa {
    //
    private String relationCode // 关联的业务数据编号
            ,itemCode //明细编号

            ,courseNum //课次
            ,week //星期
            ,weekNum //周次
            ,lessonNum; //节次
    private Integer lessonHour; //学时,课程时长
    private String skjsCode
            ,skjsName; //任课老师

    private String teachTarget, //授课目标,填写本次授课的目标。
            teachDesign //教学组织，围绕教学目标，教师采用什么方式？讲授什么内容？培养学生什么知识、能力和素养？
            ,politicElement; //课程思政点，若本次课有课程思政点，填写；若没有，则不填写。

    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date commonDate;

}
