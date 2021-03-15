package com.mycode.jxzlpj.shaungchuangjiaoyu.zdxscjcyxm.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycode.common.shenheSet.domain.ShenHeObj;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 双创教育-指导学生参加创业项目
 */
@Getter
@Setter
public class Zdxscjcyxm extends ShenHeObj {

    //业务字段
    private String code //业务数据编号
            ,projectName //项目名称
            ,level1 //项目级别，一级下拉框【国家级、省部级、校级】
            ,level2 //项目级别，二级下拉框【特等奖、一等奖（金奖）、二等奖（银奖）、三等奖（铜奖）、优秀奖】
            ,projectCategory; //项目类别【创新、创业】
    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date projectDate; //项目立项时间
    @JsonIgnore
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date datetimeStart
            ,datetimeEnd;

}