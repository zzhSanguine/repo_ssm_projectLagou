package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /*
       用户分页&多条件查询方法
    */
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo){

        PageInfo pageInfo = userService.findAllUserByPage(userVo);

        return new ResponseResult(true,200,"用户分页查询成功",pageInfo);

    }

    /*
     * 修改用户状态
     * ?? 为什么用户状态这里要用 置反的逻辑，而不是输入的status是要保存到后台的值
     * */
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(int id, String status){

        if("ENABLE".equalsIgnoreCase(status)){
            status = "DISABLE";
        }else {
            status = "ENABLE";
        }

        userService.updateUserStatus(id,status);

        return new ResponseResult(true,200,"修改用户状态成功",status);

    }

    /*
     * 用户登录(根据用户名查询具体用户信息(加密密码))
     * */
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {

        User loginUser = userService.login(user);

        if(loginUser != null){
            //保存用户id及access_token 到session中
            HttpSession session = request.getSession();

            String access_token = UUID.randomUUID().toString();
            session.setAttribute("access_token",access_token);
            session.setAttribute("user_id",loginUser.getId());

            //将查询出来的信息封装到map响应给前台
            HashMap<String , Object> map = new HashMap<>();
            map.put("access_token",access_token);
            map.put("user_id",loginUser.getId());

            //将查询出来的user 也存到map中,登出用
            map.put("user",loginUser);

            return new ResponseResult(true,1,"登录成功",map);
        }else {
            return new ResponseResult(true,400,"用户名密码错误",null);
        }
    }

    /*
     * 根据用户id查询关联角色信息(分配角色-回显)
     * */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRelationRoleById(Integer id){

        List<Role> list = userService.findUserRelationRoleById(id);

        return new ResponseResult(true,200,"分配角色回显成功",list);
    }

    /*
     * 给用户分配角色
     * */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo){

        userService.userContextRole(userVo);

        return new ResponseResult(true,200,"用户分配角色成功",null);

    }

    /*
     * 获取用户权限，动态菜单展示
     * */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){

        //获取请求中的token
        String token = request.getHeader("Authorization");

        //获取session中的access_token
        HttpSession session = request.getSession();
        String access_token = (String) session.getAttribute("access_token");

        //判断
        if(token.equalsIgnoreCase(access_token)){
            Integer user_id = (Integer) session.getAttribute("user_id");
            ResponseResult result = userService.getUserPermissions(user_id);
            return result;
        }else {
            return new ResponseResult(false,4,"获取失败",null);
        }
    }


}

