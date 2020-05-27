package com.mycode.system.menu.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Menu {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;

    //业务字段
    private Long pid
            ,menuId;
    private String menuName
            ,url
            ,icon;

    //菜单树属性
    private Long id;
    private String title;
    private List<Menu> children = new ArrayList<>();
    //
    public static List<Menu> getMenuTree(List<Menu> menuList){
        List<Menu> resultMenuList = new ArrayList<>();
        Map<Long,Menu> map = new HashMap<>();
        for (Menu menu : menuList) {
            map.put(menu.getMenuId(),menu);
        }
        for (Menu menu : menuList) {
            if(StringUtils.isEmpty(menu.getPid())){
                resultMenuList.add(menu);
            }else{
                Menu parent = map.get(menu.getPid());
                parent.getChildren().add(menu);
            }
        }
        return resultMenuList;
    }
}
