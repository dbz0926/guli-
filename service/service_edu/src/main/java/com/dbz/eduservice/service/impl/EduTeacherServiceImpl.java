package com.dbz.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dbz.eduservice.entity.EduTeacher;
import com.dbz.eduservice.entity.vo.TeacherQuery;
import com.dbz.eduservice.mapper.EduTeacherMapper;
import com.dbz.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dbz
 * @since 2022-04-01
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {
    @Autowired
    private EduTeacherMapper eduTeacherMapper;

    @Override
    public void queryPageTeacher(Page<EduTeacher> eduTeacherPage, TeacherQuery teacherQuery) {
        QueryWrapper<EduTeacher> eduTeacherQueryWrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if (!StringUtils.isEmpty(name)) {
            eduTeacherQueryWrapper.like("name", name);
        }

        if (!StringUtils.isEmpty(level) ) {
            eduTeacherQueryWrapper.eq("level", level);
        }

        if (!StringUtils.isEmpty(begin)) {
            eduTeacherQueryWrapper.ge("gmt_create", begin);
        }

        if (!StringUtils.isEmpty(end)) {
            eduTeacherQueryWrapper.le("gmt_create", end);
        }
        eduTeacherQueryWrapper.orderByDesc(begin);
        eduTeacherMapper.selectPage(eduTeacherPage,eduTeacherQueryWrapper);

    }

    @Override
    public List<EduTeacher> getFrontEightTeacher() {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        queryWrapper.last("limit 4");
        List<EduTeacher> eduTeachers = baseMapper.selectList(queryWrapper);
        return eduTeachers;
    }

    @Override
    public Map<String, Object> getTeacherByPageQuery(Page<EduTeacher> teacherPage) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        baseMapper.selectPage(teacherPage, queryWrapper);
        List<EduTeacher> records = teacherPage.getRecords();
        long current = teacherPage.getCurrent();
        long pages = teacherPage.getPages();
        long size = teacherPage.getSize();
        long total = teacherPage.getTotal();
        boolean hasNext = teacherPage.hasNext();
        boolean hasPrevious = teacherPage.hasPrevious();
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return map;
    }
}
