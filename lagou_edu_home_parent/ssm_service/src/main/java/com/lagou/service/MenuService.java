package com.lagou.service;

import com.lagou.domain.Menu;

import java.util.List;

public interface MenuService {

    /*
     * 查询所有 父子 菜单信息
     * */
    public List<Menu> findSubMenuByPid(int pid);

    /*
     * 查询所有菜单信息
     * */
    public List<Menu> findAllMenu();


    public Menu findMenuById(Integer id);
}
