package com.atguigu.serviceedu.mapper;

import com.atguigu.serviceedu.entity.EduSubject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author chairuntao
 * @since 2020-05-09
 */
@Mapper
public interface EduSubjectMapper extends BaseMapper<EduSubject> {

    List<EduSubject> getSubjects();

    List<EduSubject> getSubjectTest(@Param("id") String parentId);

    List<EduSubject> list();
}
