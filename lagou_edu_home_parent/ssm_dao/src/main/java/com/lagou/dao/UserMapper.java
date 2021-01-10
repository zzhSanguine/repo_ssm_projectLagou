package com.lagou.dao;

import com.lagou.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    /*
       用户分页&多条件查询方法
    */
    public List<User> findAllUserByPage(UserVo userVo);

    /*
     * 修改用户状态
     * */
    public void updateUserStatus(@Param("id") int id, @Param("status") String status);

    /*
    * 用户登录(根据用户名查询具体用户信息(加密密码))
    * */
    public User login(User user);

    //根据用户id清空中间表
    public void deleteUserContextRole(Integer id);

    /*
    * 分配角色
    * */
    public void userContextRole(User_Role_relation user_role_relation);

    /*
     * 1.根据用户id 查询关联角色信息,多个角色
     * */
    public List<Role> findUserRelationRoleById(Integer id);

    /*
    * 2.根据角色id，查询角色拥有的顶级菜单(-1)
    * */
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);

    /*
    * 3.根据pid，查询子菜单信息
    * */
    public List<Menu> findSubMenuByPid(Integer pid);

    /*
    * 4.根据角色id，获取用户拥有的资源权限信息
    * */
    public List<Resource> findResourceByRoleId(List<Integer> ids);











}
