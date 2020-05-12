package com.atguigu.serviceedu.service.impl;

import com.atguigu.servciebase.config.exceptionHandle.GuliException;
import com.atguigu.serviceedu.entity.EduCourse;
import com.atguigu.serviceedu.entity.EduCourseDescription;
import com.atguigu.serviceedu.entity.vo.CourseInfo;
import com.atguigu.serviceedu.mapper.EduCourseMapper;
import com.atguigu.serviceedu.service.EduCourseDescriptionService;
import com.atguigu.serviceedu.service.EduCourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author chairuntao
 * @since 2020-05-11
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    @Override
    public String saveCourseInfo(CourseInfo courseInfo) {
        //课程表基本信息添加
        EduCourse course=new EduCourse();
        BeanUtils.copyProperties(courseInfo,course);
        final int insert = baseMapper.insert(course);

        if (insert<=0){
            throw new GuliException(400,"添加课程失败");
        }

        final String courseId = course.getId();


        //2、项课程简介表添加数据
        EduCourseDescription eduCourseDescription=new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfo.getDescription());
        eduCourseDescription.setId(courseId);
        courseDescriptionService.save(eduCourseDescription);

        return courseId;

    }



    @Override
    public CourseInfo getCoursebyId(String courseId) {
        CourseInfo courseInfo=new CourseInfo();

        final EduCourse course = baseMapper.selectById(courseId);
        BeanUtils.copyProperties(course,courseInfo);

        final EduCourseDescription coursreDescription = courseDescriptionService.getById(courseId);
        courseInfo.setDescription(coursreDescription.getDescription());
        return courseInfo;
    }

    @Override
    public void updateCourseInfo(CourseInfo courseInfo) {
        EduCourse eduCourse=new EduCourse();
        BeanUtils.copyProperties(courseInfo,eduCourse);
        final int i = baseMapper.updateById(eduCourse);

        if (i==0){
            throw new GuliException(400,"课程修改失败");
        }

        EduCourseDescription eduCourseDescription=new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfo.getDescription());
        final boolean b = courseDescriptionService.updateById(eduCourseDescription);


    }
}
