package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /*
       用户分页&多条件查询方法
    */
    @Override
    public PageInfo findAllUserByPage(UserVo userVo) {

        PageHelper.startPage(userVo.getCurrentPage(),userVo.getPageSize());
        List<User> allUserByPage = userMapper.findAllUserByPage(userVo);

        PageInfo<User> pageInfo = new PageInfo<>(allUserByPage);

        return pageInfo;
    }

    /*
     * 修改用户状态
     * */
    @Override
    public void updateUserStatus(int id, String status) {

        userMapper.updateUserStatus(id,status);
    }

    /*
     * 用户登录(根据用户名查询具体用户信息(加密密码))
     * */
    @Override
    public User login(User user) throws Exception {

        User loginUser = userMapper.login(user);

        if(loginUser != null && Md5.verify(user.getPassword(),"lagou",loginUser.getPassword())){
            return loginUser;
        }else {
            return null;
        }
    }

    /*
     * 根据用户id查询关联角色信息(分配角色-回显)
     * */
    @Override
    public List<Role> findUserRelationRoleById(Integer id) {
        List<Role> list = userMapper.findUserRelationRoleById(id);
        return list;
    }

    /*
     * 给用户分配角色
     * */
    @Override
    public void userContextRole(UserVo userVo) {
        //根据用户id，清空中间表关联关系
        userMapper.deleteUserContextRole(userVo.getUserId());

        //重新建立关联关系
        for (Integer roleId : userVo.getRoleIdList()) {
            User_Role_relation user_role_relation = new User_Role_relation();

            user_role_relation.setUserId(userVo.getUserId());
            user_role_relation.setRoleId(roleId);

            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");

            userMapper.userContextRole(user_role_relation);
        }
    }

    /*
     * 获取用户权限，动态菜单展示
     * */
    @Override
    public ResponseResult getUserPermissions(Integer userId) {

        //获取当前用户拥有的角色
        List<Role> roleList = userMapper.findUserRelationRoleById(userId);
        //获取角色id，保存到list集合汇总
        ArrayList<Integer> roleIds = new ArrayList<>();

        for (Role role : roleList) {
            roleIds.add(role.getId());
        }
        //根据角色id，查询父菜单
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(roleIds);

        //查询父菜单关联的子菜单
        for (Menu menu : parentMenu) {
            List<Menu> subMenuByPid = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenuByPid);
        }

        //获取资源信息
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleIds);

        //封装数据并返回
        HashMap<String , Object> map = new HashMap<>();
        map.put("menuList",parentMenu);
        map.put("resourceList",resourceList);

        return new ResponseResult(true,200,"获取用户权限成功",map);
    }







}
