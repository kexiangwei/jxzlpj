package com.mycode.jxzlpj.jiaoxueyanjiu.jiaoxuetuandui.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 教学研究-教学团队-评审设置
 */
@Getter
@Setter
public class PingShenTemplate {

    private String code //业务编号
            ,target //指标
            ,targetName //指标名称
            ,targetElement //评审要素
            ,targetContent //评审标准
            ,targetScore; //预设分值
}
