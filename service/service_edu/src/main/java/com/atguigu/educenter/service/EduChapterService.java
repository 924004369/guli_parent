package com.atguigu.educenter.service;

import com.atguigu.educenter.entity.EduChapter;
import com.atguigu.educenter.entity.chapter.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author chairuntao
 * @since 2020-05-11
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    int deleteChapterById(String chapterId);

    List<EduChapter> getChapterByCourseId(String courseId);
}
