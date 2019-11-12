package com.mycode.system.menu.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @auther kexiangwei
 * @date 2019/7/13
 */
@Getter
@Setter
public class MenuTab {

    private Long pid,menuId;
    private String menuName,url,icon;
    private String tabName; //菜单关联的业务表名称（tab_a）

}
