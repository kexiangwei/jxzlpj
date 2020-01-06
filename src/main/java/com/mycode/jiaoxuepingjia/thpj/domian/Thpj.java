package com.mycode.jiaoxuepingjia.thpj.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 教学评价-同行评教
 * @auther kexiangwei
 * @date 2019/12/31
 */
@Getter
@Setter
public class Thpj {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    //业务字段
    private String code //业务编号
            ,testName
            ,remark; //学生人数
    //
    private Integer userId;
    private String userName;
    //
    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate; //业务数据录入时间

}
