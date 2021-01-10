package com.lagou.service.impl;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;
import com.lagou.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;


    /*
     * 多条件查询课程列表查询
     * */
    @Override
    public List<Course> findCourseByCondition(CourseVO courseVO) {
        List<Course> list = courseMapper.findCourseByCondition(courseVO);
        return list;
    }

    /*
    添加课程及讲师信息
    */
    @Override
    public void saveCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {

        //封装课程信息
        Course course = new Course();
        //补全课程信息
        BeanUtils.copyProperties(course, courseVO);//org.apache
        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);
        //保存课程
        courseMapper.saveCourse(course);
        //获取新插入数据的id
        int id = course.getId();


        //封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVO);
        //补全讲师信息
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setIsDel(0);
        teacher.setCourseId(id);
        //保存讲师信息
        courseMapper.saveTeacher(teacher);
    }

    /*根据id查询课程信息*/
    @Override
    public CourseVO findCourseById(Integer id) {
        return courseMapper.findCourseById(id);
    }

    /*
   修改课程及讲师信息
   */
    @Override
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {

        //封装课程信息
        Course course = new Course();
        //补全课程信息
        BeanUtils.copyProperties(course, courseVO);//org.apache
        Date date = new Date();
        course.setUpdateTime(date);
        //保存课程
        courseMapper.updateCourse(course);


        //封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVO);
        //补全讲师信息
        teacher.setUpdateTime(date);
        teacher.setCourseId(course.getId());
        //保存讲师信息
        courseMapper.updateTeacher(teacher);

    }

    /*课程状态管理*/
    @Override
    public void updateCourseStatus(int courseId, int status) {

        //封装数据
        Course course = new Course();
        course.setStatus(status);
        course.setId(courseId);
        course.setUpdateTime(new Date());

        //调用mapper
        courseMapper.updateCourseStatus(course);

    }


}
