package com.mycode.jiaoxuepingjia.pjset.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 教学评价-评教设置
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
