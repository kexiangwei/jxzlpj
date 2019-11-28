package com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 教学研究-教改项目-项目成员
 * @auther kexiangwei
 * @date 2019/11/13
 */
@Getter
@Setter
public class FundBudget {
    private String xmCode //项目编号
            ,subject //科目
            ,budgetAmount; //预算金额（元）
}
