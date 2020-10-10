package com.mycode.shaungchuangjiaoyu.zdxscy.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycode.commonset.shenheSet.domain.ShenHeObj;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 双创教育-指导学生创业
 */
@Getter
@Setter
public class Zdxscy extends ShenHeObj {

    //业务字段
    private String code //业务数据编号
            ,companyName //公司名称
            , businessScope ; //经营范围
    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date registDate; //注册时间
    @JsonIgnore
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date registDateStart
            ,registDateEnd;
}