package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /*
     * 查询所有角色
     * */
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){

        List<Role> list = roleService.findAllRole(role);

        ResponseResult result = new ResponseResult(true, 200, "查询所有角色成功", list);

        return result;

    }

    @Autowired
    private MenuService menuService;

    /*
     * 查询所有 父子 菜单信息
     * */
    @RequestMapping("/findAllMenu")
    public ResponseResult findSubMenuByPid(){

        List<Menu> subMenuByPid = menuService.findSubMenuByPid(-1);
        HashMap<String, Object> map = new HashMap<>();
        map.put("parentMenuList",subMenuByPid); //符合接口文档规范

        ResponseResult result = new ResponseResult(true, 200, "查询父子菜单成功", map);

        return result;
    }

    /*
     * 根据角色ID查询该角色关联的信息菜单ID[1,2,3,5]
     * */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){

        List<Integer> menuByRoleId = roleService.findMenuByRoleId(roleId);

        return new ResponseResult(true,200,"查询该角色关联的信息菜单成功",menuByRoleId);

    }

    //添加角色
    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role){

        roleService.saveRole(role);

        return new ResponseResult(true,200,"添加角色成功",null);

    }

    /*
     * 角色菜单关联
     * */
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){

        roleService.roleContextMenu(roleMenuVo);

        return new ResponseResult(true,200,"角色菜单关联成功","");

    }

    /*
     * 删除角色
     * */
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){
        roleService.deleteRole(id);

        return new ResponseResult(true,200,"删除角色成功",null);
    }




}
