package com.dbz.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dbz.eduservice.entity.EduSubject;
import com.dbz.eduservice.entity.excel.SubjectData;
import com.dbz.eduservice.service.EduSubjectService;
import com.dbz.servicebase.handler.GuliException;

import java.util.Map;

/**
 * @author 10979
 * @description:
 * @date 2022/4/420:33
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    public EduSubjectService subjectService;

    public SubjectExcelListener() {}

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData == null) {
            throw new GuliException(20001,"添加失败");
        }
        //添加一级分类
        EduSubject isExistOneSubject = this.existOneSubject(subjectService,subjectData.getOneSubjectName());
        if(isExistOneSubject == null) {//没有相同的
            isExistOneSubject = new EduSubject();
            isExistOneSubject.setTitle(subjectData.getOneSubjectName());
            isExistOneSubject.setParentId("0");
            subjectService.save(isExistOneSubject);
        }

        //获取一级分类id值
        String pid = isExistOneSubject.getId();

        //添加二级分类
        EduSubject existTwoSubject = this.existTwoSubject(subjectService,subjectData.getTwoSubjectName(), pid);
        if(existTwoSubject == null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());
            existTwoSubject.setParentId(pid);
            subjectService.save(existTwoSubject);
        }
    }
    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息："+headMap);
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {}

    //判断二级分类是否重复
    private EduSubject existTwoSubject(EduSubjectService subjectService, String name, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject eduSubject = subjectService.getOne(wrapper);
        return eduSubject;
    }

    //判断一级分类是否重复
    private EduSubject existOneSubject(EduSubjectService subjectService,String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject eduSubject = subjectService.getOne(wrapper);
        return eduSubject;
    }
}
