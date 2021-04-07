package com.mycode.jxzlpj.jiaoxuejiangcheng.kcjscgj.domian;

import com.mycode.shenheSet.domain.ShenHeObj;
import lombok.Getter;
import lombok.Setter;

/**
 * 教学奖惩-课程建设成果奖
 */
@Getter
@Setter
public class Kcjscgj extends ShenHeObj {

    //业务字段
    private String code //业务数据编号
            ,courseName //课程名称
            ,awardName  //奖项名称
            ,awardLevel //奖励级别，一级下拉框【国家级|省部级|校级】
            ,winAward //获得奖项，二级下拉框[自定义]
            ,certAuthority //证书授予机构
            ,awardDate; //获奖日期(年)

}
