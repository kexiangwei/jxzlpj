package com.mycode.common.shenhe.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 审核流程节点信息
 * @auther kexiangwei
 * @date 2019/7/27
 */
@Getter
@Setter
public class ShenHeNode {

    //业务字段
    private String roleId; //角色id
    private String roleName
            ,shenheCode//审核流程编号
            ,nodeCode // 节点编号
            ,nodeName// 节点名称
            ,nodeTask;// 节点任务
    private Integer execLevel; //执行级别
    private Date createDate;

    //节点表格数据行上下移动
    private String sortType;//排序方式 up|down
//    private Integer isDelete;// 事件，是否执行的是删除操作
}
