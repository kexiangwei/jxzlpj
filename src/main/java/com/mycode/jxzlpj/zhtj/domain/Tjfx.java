package com.mycode.jxzlpj.zhtj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @auther kexiangwei
 * @date 2021/4/10
 */
@Data
public class Tjfx {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    //查询参数
    private String xyCode
            ,zyCode;
}
