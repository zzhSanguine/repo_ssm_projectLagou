package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.domain.Role_menu_relation;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /*
     * 查询所有角色
     * */
    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> list = roleMapper.findAllRole(role);
        return list;
    }

    /*
     * 根据角色ID查询该角色关联的信息菜单ID[1,2,3,5]
     * */
    @Override
    public List<Integer> findMenuByRoleId(Integer roleId) {

        List<Integer> menuByRoleId = roleMapper.findMenuByRoleId(roleId);
        return menuByRoleId;
    }

    //添加角色
    @Override
    public void saveRole(Role role) {
        Date date = new Date();

        role.setCreatedTime(date);
        role.setUpdatedTime(date);
        role.setCreatedBy("system");
        role.setUpdatedBy("system");

        roleMapper.saveRole(role);

    }

    /*
     * 角色菜单关联
     * */
    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {

        //清空中间表的关联关系
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());

        //为角色分配菜单
        for (Integer mid : roleMenuVo.getMenuIdList()) {
            Role_menu_relation rmr = new Role_menu_relation();
            Date date = new Date();
            //封装数据
            rmr.setMenuId(mid);
            rmr.setRoleId(roleMenuVo.getRoleId());
            rmr.setCreatedTime(date);
            rmr.setUpdatedTime(date);
            rmr.setCreatedBy("system");
            rmr.setUpdatedby("system");

            roleMapper.roleContextMenu(rmr);
        }
    }

    /*
    * 删除角色
    * */
    @Override
    public void deleteRole(Integer roleId) {

        roleMapper.deleteRoleContextMenu(roleId);//先清空中间表

        roleMapper.deleteRole(roleId);
    }





}
