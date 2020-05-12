package com.atguigu.serviceedu.controller;


import com.atguigu.commonutils.ResultMap;
import com.atguigu.serviceedu.entity.vo.CourseInfo;
import com.atguigu.serviceedu.entity.vo.CoursePublishVo;
import com.atguigu.serviceedu.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author chairuntao
 * @since 2020-05-11
 */
@RestController
@RequestMapping("/eduservice/edu-course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;

    @PostMapping("/addCourseInfo")
    private ResultMap addCouresInfo(@RequestBody CourseInfo courseInfo){
        String courseId=courseService.saveCourseInfo(courseInfo);
        return ResultMap.ok().data("courseId",courseId);
    }

    @GetMapping("/{courseId}")
    public ResultMap getCoursebyId(@PathVariable String courseId){
        CourseInfo courseInfo=courseService.getCoursebyId(courseId);
        return ResultMap.ok().data("courseInfo",courseInfo);
    }

    //修改页面回显的内容
    @PostMapping("/updateCourseInfo")
    public ResultMap updateCourseInfo(@RequestBody CourseInfo courseInfo){
        courseService.updateCourseInfo(courseInfo);
        return ResultMap.ok();
    }


    //根据课程ID查询所有课程相关的信息
    @GetMapping("/getCourseInfoById/{courseId}")
    public ResultMap getCourseInfoById(@PathVariable String courseId){
        CoursePublishVo coursePublishVo=courseService.getCourseInfoById(courseId);

        return ResultMap.ok().data("coursePublishVo",coursePublishVo);

    }


}

