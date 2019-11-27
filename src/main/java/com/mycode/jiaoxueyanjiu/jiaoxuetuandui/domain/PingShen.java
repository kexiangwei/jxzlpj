package com.mycode.jiaoxueyanjiu.jiaoxuetuandui.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 教学研究-教学团队-评审
 * @auther kexiangwei
 * @date 2019/11/13
 */
@Getter
@Setter
public class PingShen {

    private String code //业务编号，系统自动生成
            ,taamCode; //团队编号
    //业务字段
    private String teamCompose //团队组成
            ,teamLeader //团队负责人
            ,jxgz //教学工作
            ,jxyj //教学研究
            ,cxcy //创新创业
            ,jspy; //教师培养
    private Double totalScore; //总分
    private String pingshenOpinion; //评审意见
    //
    private Integer userId; //评委工号
    private String userName; //评委姓名
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale="zh", timezone="GMT+8")
    private Date createDate; //数据提交时间

}
