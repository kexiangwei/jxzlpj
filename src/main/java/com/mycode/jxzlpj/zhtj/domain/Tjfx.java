package com.mycode.jxzlpj.zhtj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * 综合统计-统计分析
 */
@Data
public class Tjfx {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    //查询参数
    private String tjType //统计类型：可选值【xy/zy/js】
            ,userId //统计类型为教师时，只能查看自己的统计数据
            ,xyCode
            ,zyCode;
}
