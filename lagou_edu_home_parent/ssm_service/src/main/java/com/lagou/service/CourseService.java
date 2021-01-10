package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {


    /*
    * 多条件查询课程列表查询
    * */
    public List<Course> findCourseByCondition(CourseVO courseVO);


    /*添加课程及讲师信息*/
    public void saveCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;


    /*根据id查询课程信息*/
    public CourseVO findCourseById(Integer id);

    /*修改课程及讲师信息*/
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    /*课程状态管理*/
    public void updateCourseStatus(int courseId,int status);




}
