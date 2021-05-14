package com.mycode.jxzlpj.jiaoxuexiaoguo.sybg.domian;

import com.mycode.common.shenheSet.domain.ShenHeObj;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sybg extends ShenHeObj {

    //业务字段
    private String code //业务数据编号
            , college  //开课学院
            , major //适用专业
            , courseCode //课程编号
            , courseName; //课程名称
    private Double score; //实验课成绩

}
