package com.mycode.jiaoxuepingjia.xspj.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @auther kexiangwei
 * @date 2019/10/8
 */

@Getter
@Setter
public class Target {

    private String code,name
            ,content
            ,type;
    private Integer score,idx;
}
