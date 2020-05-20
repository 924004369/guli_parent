package com.atguigu.serviceedu.controller.front;

import com.atguigu.commonutils.ResultMap;
import com.atguigu.serviceedu.entity.EduCourse;
import com.atguigu.serviceedu.entity.EduTeacher;
import com.atguigu.serviceedu.service.EduCourseService;
import com.atguigu.serviceedu.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chairuntao
 * @version 1.0
 * @date 2020/5/15 15:04
 */
@RestController
@RequestMapping("/eduservice/edu-course")
@CrossOrigin
public class IndexFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduTeacherService teacherService;

    //查询前8条热门课程，前4个名师
    @GetMapping("/index")
    @Cacheable(key = "'index'",value = "teacherAndCourse")
    public ResultMap index(){
        QueryWrapper<EduCourse> wrapperCourse=new QueryWrapper<>();
        wrapperCourse.orderByDesc("view_count");
        wrapperCourse.last("limit 8");
        final List<EduCourse> eduCourseList = courseService.list(wrapperCourse);

        QueryWrapper<EduTeacher> wrapperTeacher=new QueryWrapper<>();
        wrapperTeacher.orderByDesc("id");
        wrapperTeacher.last("limit 4");
        final List<EduTeacher> eduTeacherList = teacherService.list(wrapperTeacher);

        return ResultMap.ok().data("eduTeacherList",eduTeacherList).data("eduCourseList",eduCourseList);
    }
}
