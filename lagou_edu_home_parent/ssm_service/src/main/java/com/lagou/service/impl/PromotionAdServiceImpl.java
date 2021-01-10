package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.PromotionAdMapper;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PromotionAdServiceImpl implements PromotionAdService {

    @Autowired
    private PromotionAdMapper promotionAdMapper;


    /*
    分页获取所有的广告列表
    */
    @Override
    public PageInfo findAllAdByPage(PromotionAdVO adVO) {

        PageHelper.startPage(adVO.getCurrentPage(),adVO.getPageSize());
        List<PromotionAd> allAdByPage = promotionAdMapper.findAllAdByPage();

        PageInfo<PromotionAd> pageInfo = new PageInfo<>(allAdByPage);

        return pageInfo;
    }

    /*
     * 新建广告
     * */
    @Override
    public void savePromotionAd(PromotionAd promotionAd) {

        Date date = new Date();
        promotionAd.setCreateTime(date);
        promotionAd.setUpdateTime(date);

        promotionAdMapper.savePromotionAd(promotionAd);
    }

    /*
     * 修改广告
     * */
    @Override
    public void updatePromotionAd(PromotionAd promotionAd) {

        Date date = new Date();
        promotionAd.setUpdateTime(date);

        promotionAdMapper.updatePromotionAd(promotionAd);
    }


    /*
     * 根据id查询广告信息
     * */
    @Override
    public PromotionAd findPromotionAdById(int id) {
        PromotionAd promotionAdById = promotionAdMapper.findPromotionAdById(id);
        return promotionAdById;
    }

    /*
    * 广告动态上下线
    * */
    @Override
    public void updatePromotionAdStatus(int id, int status) {

        //封装数据
        PromotionAd promotionAd = new PromotionAd();
        promotionAd.setId(id);
        promotionAd.setStatus(status);
        promotionAd.setUpdateTime(new Date());
        //调用mapper
        promotionAdMapper.updatePromotionAdStatus(promotionAd);

    }













}
