package com.mycode.shaungchuangjiaoyu.bkssqzl.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mycode.shenhe.domain.ShenHeObj;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 双创教育-本科生申请专利
 */
@Getter
@Setter
public class Bkssqzl extends ShenHeObj {

    //业务字段
    private String code //业务数据编号
            ,name //名称
            ,type //类型【发明专利、实用新型专利、外观设计专利、软件著作权】
            ,authNum; //授权号
    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date approvalTime; //获批时间

}