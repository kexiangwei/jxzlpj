package com.mycode.jiaoxueyanjiu.jiaogailunwen.domian;

import com.mycode.common.shenheSet.domain.ShenHeObj;
import lombok.Getter;
import lombok.Setter;

/**
 * 教学研究-教改论文
 */
@Getter
@Setter
public class JiaoGaiLunWen extends ShenHeObj {

    //业务字段
    private String code
            ,lwTitle
            ,qkName
            ,qkType
            ,qkAttr  //期刊性质，包括教育类期刊/非教育类期刊
            ,dyAuthorCode
            ,dyAuthorName
            ,dyAuthorUnit
            ,txAuthorCode
            ,txAuthorName
            ,txAuthorUnit
            ,publishDate; //发表日期：xx年
}
