package com.atguigu.educenter.service;

import com.atguigu.educenter.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author chairuntao
 * @since 2020-05-07
 */
public interface EduTeacherService extends IService<EduTeacher> {

    List<EduTeacher> getAllTeacher();

    boolean deleteByid(String id);
}
