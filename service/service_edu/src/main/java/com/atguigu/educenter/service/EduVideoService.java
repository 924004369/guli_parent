package com.atguigu.educenter.service;

import com.atguigu.educenter.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author chairuntao
 * @since 2020-05-11
 */
public interface EduVideoService extends IService<EduVideo> {

    boolean deleteVideoById(String id);

    EduVideo getVideoById(String id);


    List<EduVideo> getVideoByChapterId(String chapterId);
}
