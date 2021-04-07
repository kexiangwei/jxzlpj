package com.mycode.shenheSet.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 审核流程节点信息
 */
@Getter
@Setter
public class ShenHeNode {

    //业务字段
    private String shenheCode//审核流程编号
            ,nodeCode // 节点编号
            ,nodeName// 节点名称
            ,nodeTask;// 节点任务
    private Long roleId; //角色id
    private String roleName;
    private Integer execLevel; //执行级别
    private Date createDate;

    //节点表格数据行上下移动
    private String sortType;//排序方式 up|down

}
