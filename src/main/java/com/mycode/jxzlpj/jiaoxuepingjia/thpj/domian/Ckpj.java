package com.mycode.jxzlpj.jiaoxuepingjia.thpj.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycode.common.common.domain.Course;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 教学评价-查看同行评教
 */
@Getter
@Setter
public class Ckpj extends Course {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    private String accountType
            ,userId;
    //评审状态
    private Integer isPj; //1已评2未评
    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date pjStartDate //开始时间
            ,pjEndDate; //结束时间
}
