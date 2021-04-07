package com.mycode.system.role.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycode.system.menu.domain.Menu;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Role {

    //分页参数
    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    //业务字段
    private String roleId
            ,roleName;
    private List<Menu> menuList = new ArrayList<>();

}
