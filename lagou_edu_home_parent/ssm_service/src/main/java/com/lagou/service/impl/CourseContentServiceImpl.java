package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContentService {

    @Autowired
    private CourseContentMapper courseContentMapper;


    /*
     * 查询课程下的 章节与课时信息
     * */
    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(int courseId) {

        List<CourseSection> list = courseContentMapper.findSectionAndLessonByCourseId(courseId);
        return list;
    }


    /*
     * 回显章节对应的课程信息
     * */
    @Override
    public Course findCourseByCourseId(int courseId) {
        return courseContentMapper.findCourseByCourseId(courseId);
    }

    /*
     * 保存章节
     * */
    @Override
    public void saveSection(CourseSection section) {

        //补全信息
        Date date = new Date();
        section.setCreateTime(date);
        section.setUpdateTime(date);
        courseContentMapper.saveSection(section);

    }

    /*
     * 更新章节
     * */
    @Override
    public void updateSection(CourseSection section) {
        //补全信息
        section.setUpdateTime(new Date());
        courseContentMapper.updateSection(section);
    }

    /*
     * 修改章节状态
     * */
    @Override
    public void updateSectionStatus(int id, int status) {

        //封装数据
        CourseSection section = new CourseSection();

        section.setId(id);
        section.setStatus(status);
        section.setUpdateTime(new Date());
        courseContentMapper.updateSectionStatus(section);
    }

    /*
     * 保存课时
     * */
    @Override
    public void saveLesson(CourseLesson lesson) {

        Date date = new Date();
        lesson.setCreateTime(date);
        lesson.setUpdateTime(date);

        courseContentMapper.saveLesson(lesson);

    }








}
