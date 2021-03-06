package com.atguigu.educenter.service;

import com.atguigu.educenter.entity.EduCourse;
import com.atguigu.educenter.entity.vo.CourseInfo;
import com.atguigu.educenter.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author chairuntao
 * @since 2020-05-11
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfo courseInfo);

    CourseInfo getCoursebyId(String courseId);

    void updateCourseInfo(CourseInfo courseInfo);

    CoursePublishVo getCourseInfoById(String courseId);

    void deleteCourse(String courseId);
}
