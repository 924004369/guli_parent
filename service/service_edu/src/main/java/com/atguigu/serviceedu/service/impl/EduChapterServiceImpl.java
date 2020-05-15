package com.atguigu.serviceedu.service.impl;

import com.atguigu.servciebase.config.exceptionHandle.GuliException;
import com.atguigu.serviceedu.entity.EduChapter;
import com.atguigu.serviceedu.entity.EduVideo;
import com.atguigu.serviceedu.entity.chapter.ChapterVo;
import com.atguigu.serviceedu.entity.chapter.VideoVo;
import com.atguigu.serviceedu.mapper.EduChapterMapper;
import com.atguigu.serviceedu.mapper.EduVideoMapper;
import com.atguigu.serviceedu.service.EduChapterService;
import com.atguigu.serviceedu.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author chairuntao
 * @since 2020-05-11
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired(required = false)
    private EduChapterMapper chapterMapper;

    @Autowired
    EduVideoService videoService;
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //1、根据课程id查询出所有的章节
        QueryWrapper<EduChapter> WrapperChapter=new QueryWrapper<>();
        WrapperChapter.eq("course_id",courseId);
        final List<EduChapter> eduChapterList = chapterMapper.selectList(WrapperChapter);


        //2、根据课程id查询书所有的小节
        QueryWrapper<EduVideo> wrapperVideo=new QueryWrapper();
        wrapperVideo.eq("course_id",courseId);
        final List<EduVideo> eduVideoList = videoService.list(wrapperVideo);

        List<ChapterVo> finalList=new ArrayList<>();

        for (int i=0;i<eduChapterList.size();i++){
            final EduChapter eduChapter = eduChapterList.get(i);
            ChapterVo chapterVo=new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);


            List<VideoVo> list=new ArrayList<>();
            for (int j=0;j<eduVideoList.size();j++){
                final EduVideo eduVideo = eduVideoList.get(j);
                if (eduVideo.getChapterId().equals(eduChapter.getId())){
                    VideoVo video=new VideoVo();
                    BeanUtils.copyProperties(eduVideo,video);
                    list.add(video);
                }
            }
            chapterVo.setChildren(list);
            finalList.add(chapterVo);
        }

        return finalList;
    }

    @Override
    public int deleteChapterById(String chapterId) {
        QueryWrapper<EduVideo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("chapter_id", chapterId);
        final int count = videoService.count(queryWrapper);
        if (count>0){
            throw new GuliException(400,"章节下有小节，不能删除");
        }
        final int i = baseMapper.deleteById(chapterId);
        return i;

    }

    @Override
    public List<EduChapter> getChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        return baseMapper.selectList(queryWrapper);
    }
}
