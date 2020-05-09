package com.atguigu.serviceedu.controller;


import com.atguigu.commonutils.ResultMap;
import com.atguigu.serviceedu.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

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

}
