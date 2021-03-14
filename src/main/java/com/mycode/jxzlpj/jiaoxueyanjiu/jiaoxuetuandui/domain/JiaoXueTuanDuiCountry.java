package com.mycode.jxzlpj.jiaoxueyanjiu.jiaoxuetuandui.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mycode.common.shenheSet.domain.ShenHeObj;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 教学研究-教学团队-国家级团队
 */
@Getter
@Setter
public class JiaoXueTuanDuiCountry extends ShenHeObj {

    //业务字段
    private String code //业务编号
            ,teamName //团队名称
            ,teamLeader //团队负责人
            ,leaderUnit //单位
            ,cycle //建设周期
            ,batch; //批次（菜单，教务处管理员可进行菜单设置，如校级第几批、北京市级校级优秀育人团队、校级优秀育人团队等）
    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date commonDate; //获批时间

}
