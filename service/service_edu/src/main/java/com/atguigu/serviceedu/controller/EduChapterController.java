package com.atguigu.serviceedu.controller;


import com.atguigu.commonutils.ResultMap;
import com.atguigu.serviceedu.entity.EduChapter;
import com.atguigu.serviceedu.entity.chapter.ChapterVo;
import com.atguigu.serviceedu.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author chairuntao
 * @since 2020-05-11
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    EduChapterService chapterService;

    @GetMapping("/getChapterVideo/{courseId}")
    public ResultMap getChapterVideo(@PathVariable String courseId){
       List<ChapterVo> list = chapterService.getChapterVideoByCourseId(courseId);
        return ResultMap.ok().data("list",list);
    }

    //添加章节
    @PostMapping("/add")
    public ResultMap addChapter(@RequestBody EduChapter eduChapter){
        chapterService.save(eduChapter);
        return ResultMap.ok();
    }

    //根据章节id查询
    @GetMapping("/getChapterById/{chapterId}")
    public ResultMap getChapterbyId(@PathVariable String chapterId){
        final EduChapter eduChapter = chapterService.getById(chapterId);
        return ResultMap.ok().data("eduChapter",eduChapter);
    }

    //修改章节
    @GetMapping("/updateChapterById")
    public ResultMap updateChapterById(@RequestBody EduChapter eduChapter){
        final boolean b = chapterService.updateById(eduChapter);
        if (b){
            return ResultMap.ok();
        }
        return ResultMap.error();
    }

    //删除章节
    @DeleteMapping("/{chapterId}")
    public ResultMap deleteChapterById(@PathVariable String chapterId){
        final int i = chapterService.deleteChapterById(chapterId);
        if (i>0){
            return ResultMap.ok();
        }
        return ResultMap.error();
    }

}

