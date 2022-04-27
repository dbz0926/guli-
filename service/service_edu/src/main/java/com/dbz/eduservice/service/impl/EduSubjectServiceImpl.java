package com.dbz.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dbz.eduservice.entity.EduSubject;
import com.dbz.eduservice.entity.excel.SubjectData;
import com.dbz.eduservice.entity.subject.OneSubject;
import com.dbz.eduservice.entity.subject.TwoSubject;
import com.dbz.eduservice.listener.SubjectExcelListener;
import com.dbz.eduservice.mapper.EduSubjectMapper;
import com.dbz.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author dbz
 * @since 2022-04-04
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    @Autowired
    private EduSubjectMapper eduSubjectMapper;

    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getAllOneAndTwoSubject() {
        QueryWrapper<EduSubject> queryWrapper1=new QueryWrapper<>();
        queryWrapper1.eq("parent_id",0);
        List<EduSubject> oneSubjectList = eduSubjectMapper.selectList(queryWrapper1);

        QueryWrapper<EduSubject> queryWrapper2=new QueryWrapper<>();
        queryWrapper2.ne("parent_id",0);
        List<EduSubject> twoSubjectList = eduSubjectMapper.selectList(queryWrapper2);

        ArrayList<OneSubject> oneSubjects = new ArrayList<>();
        Map<String,OneSubject> map=new HashMap<>();
        for (EduSubject eduOneSubject:oneSubjectList){
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(eduOneSubject,oneSubject);
            oneSubjects.add(oneSubject);
            map.put(oneSubject.getId(),oneSubject);
        }
        for (EduSubject eduTwoSubject:twoSubjectList){
            String id = eduTwoSubject.getParentId();
            OneSubject oneSubject = map.get(id);
            List<TwoSubject> children = oneSubject.getChildren();
            TwoSubject twoSubject = new TwoSubject();
            BeanUtils.copyProperties(eduTwoSubject,twoSubject);
            children.add(twoSubject);
        }
            return oneSubjects;
    }
}
