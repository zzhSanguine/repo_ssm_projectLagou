package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;

import java.util.List;

public interface PromotionAdService {


    /*
    分页获取所有的广告列表
    */
    public PageInfo findAllAdByPage(PromotionAdVO adVO);

    /*
     * 新建广告
     * */
    void savePromotionAd(PromotionAd promotionAd);

    /*
     * 新建广告
     * */
    void updatePromotionAd(PromotionAd promotionAd);

    /*
     * 根据id查询广告信息
     * */
    PromotionAd findPromotionAdById(int id);

    /*
     * 广告动态上下线
     * */
    public void updatePromotionAdStatus(int id, int status);



}
