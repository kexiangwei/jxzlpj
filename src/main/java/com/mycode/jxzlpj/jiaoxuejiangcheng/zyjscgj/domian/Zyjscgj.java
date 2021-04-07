package com.mycode.jxzlpj.jiaoxuejiangcheng.zyjscgj.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mycode.shenheSet.domain.ShenHeObj;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 教学奖惩-专业建设成果奖
 */
@Getter
@Setter
public class Zyjscgj extends ShenHeObj {

    //业务字段
    private String code //业务数据编号
            ,majorCode //专业代码
            ,majorName //专业名称
            ,majorType //专业类型（设置下拉选框，下拉选框内容：国家级一流专业、省级一流专业、省级重点建设一流专业、卓越农林人才教育培养计划2.0专业）
            ,majorLeader //专业负责人
            ,leaderUnit; //所在单位
    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date passTime; //获批/通过时间

}

