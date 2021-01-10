package com.lagou.service.impl;

import com.lagou.dao.PromotionSpaceMapper;
import com.lagou.domain.PromotionSpace;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PromotionSpaceServiceImpl implements PromotionSpaceService {

    @Autowired
    private PromotionSpaceMapper promotionSpaceMapper;


    /*
    获取所有的广告位
    */
    @Override
    public List<PromotionSpace> findAllPromotionSpace() {
        List<PromotionSpace> list = promotionSpaceMapper.findAllPromotionSpace();
        return list;
    }

    /*
    添加广告位
    */
    @Override
    public void savePromotionSpace(PromotionSpace promotionSpace) {

        // 封装PromotionSpace
        UUID uuid = UUID.randomUUID();
        promotionSpace.setSpaceKey(uuid.toString());
        promotionSpace.setCreateTime(new Date());
        promotionSpace.setUpdateTime(new Date());
        promotionSpace.setIsDel(0);

        promotionSpaceMapper.savePromotionSpace(promotionSpace);

    }

    /*
     * 修改广告位
     * */
    @Override
    public void updatePromotionSpace(PromotionSpace promotionSpace) {

        promotionSpace.setUpdateTime(new Date());
        promotionSpaceMapper.updatePromotionSpace(promotionSpace);
    }

    /*
     * 根据id 查询广告位信息(回显)
     * */
    @Override
    public PromotionSpace findPromotionSpaceById(int id) {

        return promotionSpaceMapper.findPromotionSpaceById(id);

    }
}
