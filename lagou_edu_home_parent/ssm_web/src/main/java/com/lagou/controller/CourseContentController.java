package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;


    /*
     * 查询课程下的 章节与课时信息
     * */
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(Integer courseId){

        //调用service
        List<CourseSection> list = courseContentService.findSectionAndLessonByCourseId(courseId);

        ResponseResult result = new ResponseResult(true, 200, "章节及课时查询成功", list);

        return result;

    }

    /*
     * 回显章节对应的课程信息
     * */
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId){

        Course course = courseContentService.findCourseByCourseId(courseId);

        ResponseResult result = new ResponseResult(true, 200, "查询成功", course);

        return result;

    }


    /*
     * 保存(新增或者修改)章节
     * */
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection section) {

        //判断是否携带了 章节id
        if(section.getId() == null){
            courseContentService.saveSection(section);
            return new ResponseResult(true,200,"新增章节成功",null);
        }else {
            courseContentService.updateSection(section);
            return new ResponseResult(true,200,"更新章节成功",null);
        }

    }

    /*
     * 修改章节状态
     * */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(Integer id,Integer status){

        courseContentService.updateSectionStatus(id,status);

        //数据相应
        Map<String,Integer> map = new HashMap<>();
        map.put("status",status);

        ResponseResult result = new ResponseResult(true, 200, "修改章节状态成功", map);
        return result;

    }

    /*
     * 保存课时
     * */
    @RequestMapping("/saveOrUpdateLesson")
    public ResponseResult saveOrUpdateLesson(@RequestBody CourseLesson courseLesson){

        if(courseLesson .getId() == null){
            courseContentService.saveLesson(courseLesson);
            return new ResponseResult(true,200,"新增课时成功",null);
        }else {
            return null;
        }


    }
















}
