package com.atguigu.serviceedu.service.impl;

import com.atguigu.serviceedu.entity.EduVideo;
import com.atguigu.serviceedu.mapper.EduVideoMapper;
import com.atguigu.serviceedu.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
}
