package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    /*
    * 广告分页查询
    * */
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllAdByPage(PromotionAdVO promotionAdVO){

        PageInfo pageInfo = promotionAdService.findAllAdByPage(promotionAdVO);

        ResponseResult result = new ResponseResult(true, 200, "广告分页查询成功", pageInfo);
        return result;

    }

    /*
    * 图片上传
    * */
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        //1.判断接收到的上传文件是否为空
        if(file.isEmpty()){
            throw new RuntimeException();
        }

        //2.获取项目部署路径
        // D:\apache-tomcat-8.5.56\webapps\ssm_web\
        String realPath = request.getServletContext().getRealPath("/");
        // D:\apache-tomcat-8.5.56\webapps\ 截取路径
        String webappsPath = realPath.substring(0,realPath.indexOf("ssm_web"));

        //3.获取原文件名
        String fileName = file.getOriginalFilename();

        //4.新文件名
        String newFileName = System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));

        //5.上传文件
        String uploadPath = webappsPath + "upload\\";
        File filePath = new File(uploadPath,newFileName);

        //如果目录不存在就创建目录
        if(!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录: " + filePath);
        }
        //图片进行了真正的上传
        file.transferTo(filePath);

        //6.将文件名和文件路径返回
        Map<String,String> map = new HashMap<>();
        map.put("fileName",newFileName);
        map.put("filePath","http://localhost:8080/upload/" + newFileName);

        ResponseResult result = new ResponseResult(true,200,"响应成功",map);

        return result;

    }

    /*
    新增/修改广告
    */
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd){

        if(promotionAd.getId() == null){
            promotionAdService.savePromotionAd(promotionAd);
            return new ResponseResult(true,200,"新增广告成功",null);
        }else {
            promotionAdService.updatePromotionAd(promotionAd);
            return new ResponseResult(true,200,"修改广告成功",null);
        }

    }

    /*
     * 根据id查询广告信息
     * */
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(int id){

        PromotionAd promotionAdById = promotionAdService.findPromotionAdById(id);

        return new ResponseResult(true,200,"查询广告信息成功",promotionAdById);


    }

    /*
    * 广告动态上下线
    * */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionStatus(Integer id, Integer status){

        promotionAdService.updatePromotionAdStatus(id,status);

        return new ResponseResult(true,200,"广告动态上下线",null);


    }














}
