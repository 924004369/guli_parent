package com.atguigu.educenter.controller;


import com.atguigu.commonutils.ResultMap;
import com.atguigu.educenter.entity.EduSubject;
import com.atguigu.educenter.entity.subject.OneSubject;
import com.atguigu.educenter.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author chairuntao
 * @since 2020-05-09
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    //添加课程分类
    //获取上传过来的文件，把文件内容读取出来
    @PostMapping("addSubject")
    public ResultMap addSubject(MultipartFile file){
        subjectService.addSubject(file,subjectService);

        return ResultMap.ok();
    }

    @GetMapping("/")
    public ResultMap getSubject(){
        final List<EduSubject> list = subjectService.getSubject();

        return ResultMap.ok().data("list",list);
    }

    @GetMapping("/list")
    public List<EduSubject>list(){
        return subjectService.list();
    }

    //课程分类列表（树形）
    @GetMapping("/getAllSubject")
    public ResultMap getAllSubject() {
        //list集合泛型是一级分类
        List<OneSubject> list = subjectService.getAllOneTwoSubject();
        return ResultMap.ok().data("list",list);
    }

}

