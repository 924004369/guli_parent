package com.atguigu.educenter.service.impl;

import com.atguigu.educenter.entity.EduVideo;
import com.atguigu.educenter.mapper.EduVideoMapper;
import com.atguigu.educenter.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author chairuntao
 * @since 2020-05-11
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Override
    public boolean deleteVideoById(String id) {
        final int i = baseMapper.deleteById(id);
        return i>0;
    }

    @Override
    public EduVideo getVideoById(String id) {
        QueryWrapper<EduVideo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",id);
        final EduVideo eduVideo = baseMapper.selectOne(queryWrapper);
        return eduVideo;
    }


    @Override
    public List<EduVideo> getVideoByChapterId(String chapterId) {
        QueryWrapper<EduVideo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("chapter_id",chapterId);

        return baseMapper.selectList(queryWrapper);
    }
}
