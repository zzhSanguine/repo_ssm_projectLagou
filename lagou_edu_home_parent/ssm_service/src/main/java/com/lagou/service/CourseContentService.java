package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentService {

    /*
     * 查询课程下的 章节与课时信息
     * */
    public List<CourseSection> findSectionAndLessonByCourseId(int courseId);

    /*
     * 回显章节对应的课程信息
     * */
    public Course findCourseByCourseId(int courseId);

    /*
     * 新增章节
     * */
    public void saveSection(CourseSection section);

    /*
    * 更新章节
    * */
    public void updateSection(CourseSection section);

    /*
    * 修改章节状态
    * */
    public void updateSectionStatus(int id,int status);

    /*
     * 保存课时
     * */
    public void saveLesson(CourseLesson lesson);











}
