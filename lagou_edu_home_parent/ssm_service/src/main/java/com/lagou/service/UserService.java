package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {


    /*
       用户分页&多条件查询方法
    */
    public PageInfo findAllUserByPage(UserVo userVo);

    /*
     * 修改用户状态
     * */
    public void updateUserStatus(@Param("id") int id, @Param("status") String status);

    /*
     * 用户登录(根据用户名查询具体用户信息(加密密码))
     * */
    public User login(User user) throws Exception;

    /*
     * 根据用户id查询关联角色信息(分配角色-回显)
     * */
    public List<Role> findUserRelationRoleById(Integer id);

    /*
    * 给用户分配角色
    * */
    public void userContextRole(UserVo userVo);

    /*
    * 获取用户权限，动态菜单展示
    * */
    public ResponseResult getUserPermissions(Integer userId);








}
