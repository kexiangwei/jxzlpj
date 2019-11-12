package com.mycode.common.file.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @auther kexiangwei
 * @date 2019/7/16
 */
@Getter
@Setter
public class FileInfo {

    private String code
            ,relationCode;//关联的信息编号
    private String fileCategory //一级分类,你买衣服要买衬衫，牛仔裤，帽子，鞋，这是一级,这里类比模块名称
        ,fileType // 二级分类，衬衫要长袖，半袖，花格，白色，黑色，这是二级，这里类比文件类别【结业证书|参会照片|其他文件】
        ,fileName
         ,filePath;
    private Double fileSize;
    private String userId
            ,userName;
    @JsonFormat(pattern="yyyy-MM-dd", locale="zh", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createDate;

}
