package com.mycode.jxzlpj.jiaoxueyanjiu.jiaocaijianshe.domian;

import com.mycode.shenheSet.domain.ShenHeObj;
import lombok.Getter;
import lombok.Setter;

/**
 * 教学研究-教材建设
 */
@Getter
@Setter
public class JiaoCaiJianShe extends ShenHeObj {

    //业务字段
    private String code //编号
            ,name //名称
            ,category //类别：教材，专著（专著、译著、辞书）
            ,participationType //参与形式：主编、副主编、参编
            ,isbn
            ,publishers //出版社
            ,publishingTime //出版时间
            ,selected //教材入选情况：国家规划教材、省部级规划教材、国家级精品教材、省部级精品教材、其他
            ,selectedTime; //入选时间
}
