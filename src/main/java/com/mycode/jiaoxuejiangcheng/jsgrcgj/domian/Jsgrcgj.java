package com.mycode.jiaoxuejiangcheng.jsgrcgj.domian;

import com.mycode.common.shenheSet.domain.ShenHeObj;
import lombok.Getter;
import lombok.Setter;

/**
 * 教学奖惩-教师个人成果奖
 */
@Getter
@Setter
public class Jsgrcgj extends ShenHeObj {

    //业务字段
    private String code //业务数据编号
            ,awardType //获奖类别（下拉菜单-教学名师、教师教学竞赛获奖、优秀教学管理人员，下拉内容可编辑）
            ,awardLevel //获奖级别（下拉菜单：国家级、省部级、校级）
            ,awardContent //获奖内容（注明：写证书上的奖励全称，包括获奖的奖项）
            ,awardDate //获奖日期(年)
            ,certNum //证书编号
            ,certAuthority; //证书授予机构

}
