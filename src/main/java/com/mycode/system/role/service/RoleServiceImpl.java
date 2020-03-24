package com.mycode.system.role.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.system.role.mapper.RoleMapper;
import com.mycode.system.menu.domain.Menu;
import com.mycode.system.role.domain.Role;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther kexiangwei
 * @date 2019/7/13
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Map<String,Object> getRoleList(Role role) {
        Map<String, Object> map = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(role.getPageIndex(), role.getPageSize());
        List<Role> pageList = roleMapper.getRoleList(role);
        for (Role obj : pageList) {
            List<Menu> menuList = roleMapper.getMenuByRoleId(obj.getRoleId());
            obj.setMenuList(getMenuTree(menuList));
        }
        map.put("totalNum",pageInfo.getTotal());
        map.put("pageList", pageList);
        return map;
    }
    public List<Menu> getMenuTree(List<Menu> menuList){
        List<Menu> resultMenuList = new ArrayList<>();
        Map<Long,Menu> map = new HashMap<>();
        for (Menu menu : menuList) {
            map.put(menu.getMenuId(),menu);
        }
        for (Menu menu : menuList) {
            if(org.springframework.util.StringUtils.isEmpty(menu.getPid())){
                resultMenuList.add(menu);
            }else{
                Menu parent = map.get(menu.getPid());
                parent.getChildren().add(menu);
            }
        }
        return resultMenuList;
    }

    @Override
    public List<Role> getRoleListByMenuId(Long menuId) {
        List<Role> roleList = roleMapper.getRoleListByMenuId(menuId);
       /* roleList.forEach((role)-> {
            if (role.getRoleId().equals("1")) {
                role.setDisabled(true);
            } else {
                role.setDisabled(false);
            }
        });*/
        return roleList;
    }

    @Override
    public boolean insertOrUodateRoleMenu(String roleId, String roleName, String[] menuIdArr) {
        boolean bool =false;
        if(org.springframework.util.StringUtils.isEmpty(roleId)){
            roleId = StringUtils.guid(16,true);
            bool = roleMapper.addRole(roleId,roleName);
            if(bool){
                bool = roleMapper.addRoleMenu(roleId,menuIdArr);
            }
        }else{
            bool = roleMapper.updateRole(roleId,roleName);
            if(bool){
                List<Menu> menuList = roleMapper.getMenuByRoleId(roleId);
                if(menuList!=null && menuList.size()>0){//执行删除时，没有记录mybatis会返回false
                    bool = roleMapper.deleteRoleMenuByRoleId(roleId);
                    if(!bool){
                        return false;
                    }
                }
                bool = roleMapper.addRoleMenu(roleId,menuIdArr);
            }
        }
        return bool;
    }

}
