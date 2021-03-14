package com.mycode.jxzlpj.diaochawenjuan.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class WjSet {
    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    private String userId
            ,userName;
    private Integer isTrue; //问卷状态，是否填写【1是2否】
    //
    private String wjCode
            ,wjName
            ,wjDesc
            ,qCode
            ,qType
            ,qContent;
    private List<Map<String,Object>> wjSetOptList;
    //springboot接收对象数组
    private List<Map<String,Object>> qList;
}
