package com.atguigu.demo;

import com.atguigu.educenter.entity.EduChapter;
import com.atguigu.educenter.mapper.EduSubjectMapper;
import com.atguigu.educenter.service.EduChapterService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chairuntao
 * @version 1.0
 * @date 2020/5/9 9:12
 */
public class test {
    @Autowired
    EduSubjectMapper eduSubjectMapper;

    @Autowired
    EduChapterService chapterService;
    public static void main(String[] args) {
        QueryWrapper<EduChapter> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("course_id","1261110756242423810");
    }
}
