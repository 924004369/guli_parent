package com.atguigu.educenter.service.impl;

import com.atguigu.educenter.entity.EduTeacher;
import com.atguigu.educenter.mapper.EduTeacherMapper;
import com.atguigu.educenter.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author chairuntao
 * @since 2020-05-07
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {
    @Autowired
    EduTeacherMapper eduTeacherMapper;
    @Override
    public List<EduTeacher> getAllTeacher() {
        return eduTeacherMapper.selectList(null);
    }

    @Override
    public boolean deleteByid(String id) {
        if (eduTeacherMapper.deleteById(id)>0){
            return true;
        }
        return false;
    }
}
