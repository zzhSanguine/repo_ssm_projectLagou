package com.lagou.service.impl;

import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /*
     * 查询所有 父子 菜单信息
     * */
    @Override
    public List<Menu> findSubMenuByPid(int pid) {
        List<Menu> list = menuMapper.findSubMenuByPid(pid);
        return list;
    }

    /*
     * 查询所有菜单信息
     * */
    @Override
    public List<Menu> findAllMenu() {

        List<Menu> allMenu = menuMapper.findAllMenu();
        return allMenu;
    }

    @Override
    public Menu findMenuById(Integer id) {
        return menuMapper.findMenuById(id);
    }
}
