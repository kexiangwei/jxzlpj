package com.mycode.diaochawenjuan.domian;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * @auther kexiangwei
 * @date 2019/10/8
 */

@Getter
@Setter
public class Question {

    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    private String code,content,contentType,type;

}
