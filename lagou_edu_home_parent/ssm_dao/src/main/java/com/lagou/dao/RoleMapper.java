package com.lagou.dao;

import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;

import java.util.List;

public interface RoleMapper {

    /*
    * 查询所有角色
    * */
    public List<Role> findAllRole(Role role);

    /*
    * 根据角色ID查询该角色关联的信息菜单ID[1,2,3,5]
    * */
    public List<Integer> findMenuByRoleId(Integer roleId);

    //添加角色
    public void saveRole(Role role);

    /*
    * 根据roleId 清空中间表的关联关系
    * */
    public void deleteRoleContextMenu(Integer rid);

    /*
    * 角色菜单关联
    * */
    public void roleContextMenu(Role_menu_relation role_menu_relation);

    /*
    * 删除角色
    * */
    public void deleteRole(Integer roleId);










}
