package com.mycode.jiaoxuejiangcheng.qtbkjxjl.domian;

import com.mycode.commonset.shenheSet.domain.ShenHeObj;
import lombok.Getter;
import lombok.Setter;

/**
 * 教学奖惩-其他本科教学奖励
 */
@Getter
@Setter
public class Qtbkjxjl extends ShenHeObj {

    //业务字段
    private String code //业务数据编号
            ,awardName  //奖项名称
            ,awardType //奖项类别，下拉选项自定义
            ,awardDate //获奖日期(年)
            ,awardLevel //奖励级别，下拉选项【国家级|省部级|校级】
            ,winAward //获得奖项，下拉选项自定义
            ,certAuthority; //证书授予机构

}
