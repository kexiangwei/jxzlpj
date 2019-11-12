package com.mycode.diaochawenjuan.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @auther kexiangwei
 * @date 2019/10/8
 */

@Getter
@Setter
public class WjSet {

    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    private String code,name,desc,remark;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date createDate,lastModifyDate;

}
