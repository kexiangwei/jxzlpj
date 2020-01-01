package com.mycode.jiaoxuesheji.skjh.domian;

import lombok.Getter;
import lombok.Setter;

/**
 * 教学设计-授课计划条目
 * @auther kexiangwei
 * @date 2019/12/31
 */
@Getter
@Setter
public class SkjhItem {

    private String relationCode; // 关联的业务数据编号
    private Integer batchNum; //业务数据提交批次
    private String code
            ,date //日期
            ,week //星期
            ,weekNum //周次
            ,teachNum //节次
            ,teachHour //学时
            ,teachTeacher //任课老师
            ,teachAddr; //授课地点
    private String courseDesign_content //课程设计-内容
            ,courseDesign_sklx //课程设计-授课类型
            ,courseDesign_jxfs //课程设计-教学方式
            ,courseDesign_khfs; //课程设计-考核方式


}
