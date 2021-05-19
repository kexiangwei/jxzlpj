package com.mycode.jxzlpj.jiaoxuesheji.kcjxssfa.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycode.common.common.domain.Course;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 教学设计-课程教学实施方案
 */
@Getter
@Setter
public class Kcjxssfa extends Course {

    //分页查询参数
    @JsonIgnore
    private Integer pageIndex=1 //页数，默认为第一页
            ,pageSize=10; //页面尺寸，默认每页10条数据
    private Integer isTxfa; //是否填写方案，可选值【1是2否】
    //业务字段
    private String code; //业务数据编号
    private String accountType
            ,userId
            ,userName;
    //数据录入时间
    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createDate;
}
