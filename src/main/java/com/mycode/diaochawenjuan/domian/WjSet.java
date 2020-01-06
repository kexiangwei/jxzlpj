package com.mycode.diaochawenjuan.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 调查问卷-问卷设置
 * @auther kexiangwei
 * @date 2019/10/8
 */

@Getter
@Setter
public class WjSet {

    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    private List<WjQuestion> wjQuestionList;

    private String wjCode
            ,wjName
            ,wjDesc
            ,userId
            ,userName;
    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createDate;

}
