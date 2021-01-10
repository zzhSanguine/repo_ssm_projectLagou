package com.lagou.dao;

import com.lagou.domain.Menu;

import java.util.List;

public interface MenuMapper {

    /*
    * 查询所有 父子 菜单信息
    * */
    public List<Menu> findSubMenuByPid(int pid);

    /*
    * 查询所有菜单信息
    * */
    public List<Menu> findAllMenu();


    public Menu  findMenuById(Integer id);

}
