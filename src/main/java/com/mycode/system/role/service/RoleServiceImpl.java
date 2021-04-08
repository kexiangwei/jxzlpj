package com.mycode.system.role.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.system.menu.domain.Menu;
import com.mycode.system.role.domain.Role;
import com.mycode.system.role.mapper.RoleMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Map<String,Object> getRolePageList(Role role) {
        Map<String, Object> map = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(role.getPageIndex(), role.getPageSize());
        List<Role> pageList = roleMapper.getRolePageList(role);
        for (Role r : pageList) {
            List<Menu> menuList = roleMapper.getMenuListByRoleId(r.getRoleId());
            r.setMenuList(Menu.getMenuTree(menuList));
        }
        map.put("totalNum",pageInfo.getTotal());
        map.put("pageList", pageList);
        return map;
    }

    @Override
    public List<Role> getRoleList() {
        return roleMapper.getRoleList();
    }

    @Override
    @Transactional
    public boolean insertOrUpdateRoleMenu(String roleId, String roleName, String[] menuIds) {
        boolean bool = false;
        if(StringUtils.isEmpty(roleId)){
            roleId = StringUtils.uuid();
            bool = roleMapper.insertRole(roleId,roleName);
            if(bool){
                if(menuIds != null && menuIds.length >0){
                    bool = roleMapper.addRoleMenu(roleId,menuIds);
                }
            }
        }else{
            bool = roleMapper.updateRole(roleId,roleName);
            if(bool){
                List<Menu> menuList = roleMapper.getMenuListByRoleId(roleId);
                if(menuList !=null && menuList.size() >0){
                    bool = roleMapper.deleteRoleMenuByRoleId(roleId); //执行删除时，没有记录mybatis会返回false
                }
                if(menuIds != null && menuIds.length >0){
                    bool = roleMapper.addRoleMenu(roleId,menuIds);
                }
            }
        }
        return bool;
    }

}
