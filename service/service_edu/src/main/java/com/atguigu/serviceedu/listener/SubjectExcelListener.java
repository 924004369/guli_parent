package com.atguigu.serviceedu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.servciebase.config.exceptionHandle.GuliException;
import com.atguigu.serviceedu.entity.EduSubject;
import com.atguigu.serviceedu.entity.excel.SubjectData;
import com.atguigu.serviceedu.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.util.StringUtils;



/**
 * @author chairuntao
 * @version 1.0
 * @date 2020/5/9 15:22
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    public EduSubjectService subjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(SubjectData data, AnalysisContext analysisContext) {
        if (data == null) {
            throw new GuliException(400, "文件数据为空");
        }
        final EduSubject one = this.exitOneSubject(data.getOneSubjcetName(), subjectService);
        if (StringUtils.isEmpty(one)){
            EduSubject subject=new EduSubject();
            subject.setTitle(data.getOneSubjcetName());
            subject.setParentId("0");
            subject.setSort(0);
            subjectService.save(subject);
        }

        final EduSubject two = this.exitTwoSubject(data.getTwoSubjcetName(), subjectService, one.getId());
        if (StringUtils.isEmpty(two)){
            EduSubject eduSubject1=new EduSubject();
            eduSubject1.setTitle(data.getTwoSubjcetName());
            eduSubject1.setParentId(one.getId());
            eduSubject1.setSort(0);
            subjectService.save(eduSubject1);
        }


    }

    //判断一级分类不能重复添加
    private EduSubject exitOneSubject(String name,EduSubjectService subjectService){
        QueryWrapper<EduSubject> queryWrapper=new QueryWrapper();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id",0);
        final EduSubject one = subjectService.getOne(queryWrapper);
        return one;
    }

    //判断二级分类不能重复
    private EduSubject exitTwoSubject(String name,EduSubjectService subjectService,String pid){
        QueryWrapper<EduSubject> queryWrapper=new QueryWrapper();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id",pid);
        final EduSubject two = subjectService.getOne(queryWrapper);
        return two;
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
