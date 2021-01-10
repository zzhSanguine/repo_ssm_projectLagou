package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /*
    * 多条件课程列表查询
    * */
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO){

        //调用service
        List<Course> list = courseService.findCourseByCondition(courseVO);

        ResponseResult responseResult = new ResponseResult(true, 200, "相应成功", list);

        return  responseResult;

    }

    /*
    * 课程图片上传
    * */
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException {

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
    * 新增/修改 课程信息及讲师信息
    * */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {

        if(courseVO.getId() == null){ // 为空是 新增
            courseService.saveCourseOrTeacher(courseVO);
            ResponseResult result = new ResponseResult(true, 200, "新增成功", null);
            return result;
        }else {
            courseService.updateCourseOrTeacher(courseVO);
            ResponseResult result = new ResponseResult(true, 200, "修改成功", null);
            return result;
        }

    }

    /*根据id查询课程信息*/
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){

        CourseVO courseVO = courseService.findCourseById(id);

        ResponseResult result = new ResponseResult(true, 200, "响应成功", courseVO);

        return result;

    }

    /*课程状态管理*/
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(Integer id, Integer status){

        //调用service，传递参数，完成课程状态的变更
        courseService.updateCourseStatus(id, status);

        //响应数据
        Map<String, Integer> map = new HashMap<>();
        map.put("status",status);

        ResponseResult result = new ResponseResult(true, 200, "课程状态变更成功", map);

        return result;


    }













}
