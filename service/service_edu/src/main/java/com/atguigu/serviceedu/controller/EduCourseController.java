package com.atguigu.serviceedu.controller;


import com.atguigu.commonutils.ResultMap;
import com.atguigu.serviceedu.entity.EduCourse;
import com.atguigu.serviceedu.entity.vo.CourseInfo;
import com.atguigu.serviceedu.entity.vo.CoursePublishVo;
import com.atguigu.serviceedu.entity.vo.CourseQuery;
import com.atguigu.serviceedu.service.EduCourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //课程的最终发布状态
    @PostMapping("/publishCourse/{id}")
    public ResultMap publishCourse(@PathVariable String id){
        EduCourse course=new EduCourse();
        course.setId(id);
        course.setStatus("Normal");//设置课程发布状态，normal表示已发布，Draft表示未发布
        final boolean b = courseService.updateById(course);
        if (b){
            return ResultMap.ok();
        }
        return ResultMap.error();
    }


    //删除课程
    @DeleteMapping("/{courseId}")
    public ResultMap deleteCourse(@PathVariable String courseId){
        courseService. deleteCourse(courseId);
        return ResultMap.ok().message("删除成功");
    }

    @GetMapping
    public ResultMap getCourseList() {
        List<EduCourse> list = courseService.list(null);
        return ResultMap.ok().data("list",list);
    }

    //分页加条件查询
    @PostMapping("/page/{currentPage}/{size}")
    public ResultMap pageByCondition(@PathVariable(value = "currentPage")Long currentPage,
                                     @PathVariable(value = "size")Long size,
                                     @RequestBody(required = false) CourseQuery courseQuery){
        Page<EduCourse> page=new Page<>(currentPage,size);

        QueryWrapper<EduCourse> queryWrapper=new QueryWrapper<>();

        if (!StringUtils.isEmpty(courseQuery.getStatus())){
            queryWrapper.eq("status",courseQuery.getStatus());
        }
        if (!StringUtils.isEmpty(courseQuery.getTitle())){
            queryWrapper.like("title",courseQuery.getTitle());
        }
        final IPage<EduCourse> page1 = courseService.page(page, queryWrapper);
        Map<String,Object> map=new HashMap<>();
        map.put("total",page1.getTotal());
        map.put("list",page1.getRecords());
        return ResultMap.ok().data(map);
    }
}

