package com.mycode.shaungchuangjiaoyu.wtbs.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycode.shenhe.domain.ShenHeObj;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 双创教育-文、体类比赛
 */
@Getter
@Setter
public class Wtbs extends ShenHeObj {

    //查询字段
    @JsonIgnore
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date zsGrantDateStart
            ,zsGrantDateEnd;

    //业务字段
    private String code //业务数据编号
            ,name //赛事名称
            ,type //赛事类型（下拉菜单：团体、个人）；
            ,level1 //获奖等级，一级下拉框【国家级、省部级、校级】
            ,level2 //获得奖项，二级下拉框【特等奖、一等奖（金奖）、二等奖（银奖）、三等奖（铜奖）、优秀奖】
            ,zsNum // 编号字段（可为空）
            ,zsGrantUnit; //证书授予单位
    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date zsGrantDate; //证书授予时间

}