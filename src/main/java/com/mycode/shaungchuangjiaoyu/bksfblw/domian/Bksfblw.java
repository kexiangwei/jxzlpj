package com.mycode.shaungchuangjiaoyu.bksfblw.domian;

import com.mycode.common.shenheSet.domain.ShenHeObj;
import lombok.Getter;
import lombok.Setter;

/**
 * 双创教育-本科生发表论文
 */
@Getter
@Setter
public class Bksfblw extends ShenHeObj {

    //业务字段
    private String code //业务数据编号
            ,lwTitle //论文题目
            ,qkName //期刊名称
            ,includStatus //收录情况【SCI、SSCI、EI、CPCI、A&HCI、CSCD、CSSCI、北大中文核心期刊、其他期刊】
            ,publishYear; //发表时间：xx年

}