package com.mycode.jxzlpj.jiaoxuejiangcheng.jyjxcgj.domian;

import com.mycode.shenheSet.domain.ShenHeObj;
import lombok.Getter;
import lombok.Setter;

/**
 * 教学奖惩-教育教学成果奖
 */
@Getter
@Setter
public class Jyjxcgj extends ShenHeObj {

    //业务字段
    private String code //业务数据编号
            ,objName //成果名称
            ,level1 //级别，一级下拉框【国家级|北京市级|校级】
            ,level2 //奖项，二级下拉框[特等奖|一等奖|二等奖|三等奖]
            ,personRank //本人排名
            ,unitRank //完成单位排名
            ,grantUnit //授予单位
            ,datetimeYear; //获奖时间
}
