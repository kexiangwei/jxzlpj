package com.mycode.jxzlpj.jiaoxuepingjia.thpj.domian;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycode.common.common.domain.Course;
import lombok.Getter;
import lombok.Setter;

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
    private String userId;
    //评审状态
    private Integer isPj; //1已评2未评
    private String templateCode; //评教模板编号

}
