package com.mycode.system.menu.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther kexiangwei
 * @date 2019/7/13
 */
@Getter
@Setter
public class Menu {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;

    //业务字段
    private Long pid,menuId;
    private String menuName,url,icon;
    private List<Menu> children = new ArrayList<>();

    //菜单树属性
    private Long id;
    private String title;
    private Boolean checked
            ,spread;
}
