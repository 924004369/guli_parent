package com.atguigu.serviceedu.controller;


import com.atguigu.commonutils.ResultCode;
import com.atguigu.commonutils.ResultMap;
import com.atguigu.serviceedu.entity.EduTeacher;
import com.atguigu.serviceedu.entity.vo.TeacherQuery;
import com.atguigu.serviceedu.service.EduTeacherService;
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
 * 讲师 前端控制器
 * </p>
 *
 * @author chairuntao
 * @since 2020-05-07
 */
@RestController
@RequestMapping("/serviceedu/edu-teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping("/")
    public ResultMap getAllTeacher() {
        final List<EduTeacher> list = eduTeacherService.getAllTeacher();
        if (list.size()>0){
            return ResultMap.ok().code(ResultCode.SUCCESS).data("teacher",list).message("成功").success(true);
        }
        return ResultMap.error().code(ResultCode.ERROR).message("失败").success(false);
    }

    @DeleteMapping("/{id}")
    public ResultMap deleteByid(@PathVariable String id){
        if (eduTeacherService.deleteByid(id)){
            return ResultMap.ok().success(true).message("删除成功").code(ResultCode.SUCCESS);
        }
        return ResultMap.error().message("删除失败").code(ResultCode.ERROR).success(false);
    }

    @GetMapping("/pageTeacher/{currentPage}/{size}")
    public ResultMap pageListTeacher(@PathVariable( value = "currentPage") Long currentPage,
                             @PathVariable(value = "size") Long size){
        Page<EduTeacher> page=new Page<>(currentPage,size);
        QueryWrapper<EduTeacher> queryWrapper=new QueryWrapper();
        Integer result=10/0;
        queryWrapper.eq("level",1);
        final IPage<EduTeacher> page1 = eduTeacherService.page(page, queryWrapper);
        final long total = page1.getTotal();
        final List<EduTeacher> records = page1.getRecords();
        Map map=new HashMap();
        map.put("total",total);
        map.put("rows",records);
        return ResultMap.ok().data(map);
    }

    @PostMapping("/pageTeacherCondition/{currentPage}/{size}")
    public ResultMap pageTeacherCondition(@PathVariable(value = "currentPage")Long currentPage,
                                          @PathVariable(value = "size")Long size,
                                          @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<EduTeacher> page=new Page<>(currentPage,size);
        QueryWrapper<EduTeacher> queryWrapper=new QueryWrapper<>();
        if (!StringUtils.isEmpty(teacherQuery.getName())){
            queryWrapper.like("name",teacherQuery.getName());
        }
        if(!StringUtils.isEmpty(teacherQuery.getLevel())){
            queryWrapper.eq("level",teacherQuery.getLevel());
        }
        if(!StringUtils.isEmpty(teacherQuery.getBegin())){
            queryWrapper.ge("gmt_create",teacherQuery.getBegin());
        }
        if(!StringUtils.isEmpty(teacherQuery.getEnd())){
            queryWrapper.le("gmt_create",teacherQuery.getEnd());
        }
        final IPage<EduTeacher> page1 = eduTeacherService.page(page, queryWrapper);
        final long total = page1.getTotal();
        final List<EduTeacher> records = page1.getRecords();
        Map<String,Object> map=new HashMap<>();
        map.put("total",total);
        map.put("rows",records);
        return ResultMap.ok().data(map);
    }

    @PostMapping("/addTeacher")
    public ResultMap addTeacher(@RequestBody EduTeacher eduTeacher){
        final boolean save = eduTeacherService.save(eduTeacher);
        if (save){
            return ResultMap.ok().message("添加讲师成功");
        }
        return ResultMap.error().message("添加讲师失败");
    }

    @GetMapping("/getTeacher/{id}")
    public ResultMap getTeacher(@PathVariable String id){
        final EduTeacher teacher = eduTeacherService.getById(id);
        return ResultMap.ok().data("teacher",teacher);
    }

    @PostMapping("/updateTeacher")
    public ResultMap updateTeacher(@RequestBody EduTeacher eduTeacher){
        final boolean flag = eduTeacherService.updateById(eduTeacher);
        if (flag){
            return ResultMap.ok().message("修改成功");
        }
        return ResultMap.error().message("修改失败");
    }
}

