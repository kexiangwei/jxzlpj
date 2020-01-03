package com.mycode.jiaoxuepingjia.pjset.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 教学评价-评教设置-指标
 * @auther kexiangwei
 * @date 2019/10/8
 */

@Getter
@Setter
public class PjSetTarget {

    private String targetCode
            ,targetCategory
            ,targetType
            ,targetName
            ,targetContent;
    private Integer targetScore;

}
