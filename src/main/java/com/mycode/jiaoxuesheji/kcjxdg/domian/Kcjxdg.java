package com.mycode.jiaoxuesheji.kcjxdg.domian;

import com.mycode.common.shenheSet.domain.ShenHeObj;
import lombok.Getter;
import lombok.Setter;

/**
 * 教学设计-课程教学大纲
 */
@Getter
@Setter
public class Kcjxdg extends ShenHeObj {

    //业务字段
    private String code //业务数据编号
            , courseCode //课程编号
            , courseName //课程名称
            , courseAttr //课程性质
            , major //适用专业
            , college //开课学院
            , term;  //开课学期
}
