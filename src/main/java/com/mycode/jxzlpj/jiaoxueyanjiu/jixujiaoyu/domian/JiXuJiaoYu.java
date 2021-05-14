package com.mycode.jxzlpj.jiaoxueyanjiu.jixujiaoyu.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mycode.common.shenheSet.domain.ShenHeObj;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 教学研究-继续教育
 */
@Getter
@Setter
public class JiXuJiaoYu extends ShenHeObj {

    //业务字段
    private String code //业务编号
            ,peixunName //培训名称
            ,peixunStyle //培训形式
            ,peixunContent //培训内容
            ,peixunAddress //培训地点
            ,peixunDept; //培训机构
    private Integer peixunClassHour; //学时
    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date peixunStartTime //培训开始时间
            ,peixunEndTime; //培训结束时间
}
