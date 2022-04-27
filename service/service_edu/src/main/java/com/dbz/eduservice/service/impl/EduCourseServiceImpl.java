package com.dbz.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dbz.commonutils.Result;
import com.dbz.eduservice.client.VodClient;
import com.dbz.eduservice.entity.EduChapter;
import com.dbz.eduservice.entity.EduCourse;
import com.dbz.eduservice.entity.EduCourseDescription;
import com.dbz.eduservice.entity.EduVideo;
import com.dbz.eduservice.entity.vo.CourseInfoVo;
import com.dbz.eduservice.entity.vo.CoursePublishVo;
import com.dbz.eduservice.entity.vo.CourseQuery;
import com.dbz.eduservice.entity.vo.frontvo.CourseQueryParam;
import com.dbz.eduservice.entity.vo.frontvo.CourseWebVo;
import com.dbz.eduservice.mapper.EduCourseMapper;
import com.dbz.eduservice.service.EduChapterService;
import com.dbz.eduservice.service.EduCourseDescriptionService;
import com.dbz.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dbz.eduservice.service.EduVideoService;
import com.dbz.servicebase.handler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author dbz
 * @since 2022-04-05
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private VodClient vodClient;

    @Override
    public String addCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse newEduCourse = new EduCourse();
        EduCourseDescription newEduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo,newEduCourse);
        int insert = baseMapper.insert(newEduCourse);
        if (insert==0){
            throw new GuliException(20001,"添加课程失败");
        }

        String id = newEduCourse.getId();
        newEduCourseDescription.setId(id);
        newEduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescriptionService.save(newEduCourseDescription);
        return id;
    }

    @Override
    public CourseInfoVo getCourseInfoById(String courseId) {
        EduCourse eduCourse = baseMapper.selectById(courseId);
        if(eduCourse == null){
            throw new GuliException(20001, "数据不存在");
        }
        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);
        courseInfoVo.setDescription(eduCourseDescription.getDescription());
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int i = baseMapper.updateById(eduCourse);
        if (i==0){
            throw new GuliException(20001,"全局异常执行");
        }
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(courseInfoVo.getId());
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescriptionService.updateById(eduCourseDescription);
    }

    @Override
    public CoursePublishVo getCoursePublishVoByCourseId(String courseId) {
        CoursePublishVo coursePublishVo = baseMapper.getCoursePublishVo(courseId);
        return coursePublishVo;
    }

    @Override
    public void conditionPageQuery(Page<EduCourse> coursePage, CourseQuery courseQuery) {

        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");

        if (courseQuery == null){
            baseMapper.selectPage(coursePage, queryWrapper);
            return;
        }

        String title = courseQuery.getTitle();
        String teacherId = courseQuery.getTeacherId();
        String subjectParentId = courseQuery.getSubjectParentId();
        String subjectId = courseQuery.getSubjectId();

        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }

        if (!StringUtils.isEmpty(teacherId) ) {
            queryWrapper.eq("teacher_id", teacherId);
        }

        if (!StringUtils.isEmpty(subjectParentId)) {
            queryWrapper.ge("subject_parent_id", subjectParentId);
        }

        if (!StringUtils.isEmpty(subjectId)) {
            queryWrapper.ge("subject_id", subjectId);
        }

        baseMapper.selectPage(coursePage, queryWrapper);
    }

    @Override
    public boolean removeCourseById(String id) {
        //1.删除小节Video

        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",id);
        List<EduVideo> list = eduVideoService.list(queryWrapper);
        List<String> ids=new ArrayList<>();
        for (EduVideo eduVideo:list){
            if (!StringUtils.isEmpty(eduVideo.getVideoSourceId()))
            ids.add(eduVideo.getVideoSourceId());
        }
        if (!ids.isEmpty()){
            vodClient.removeBatchVideo(ids);
        }
        boolean video = eduVideoService.remove(queryWrapper);
        //2.删除章节chapter
        QueryWrapper<EduChapter> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("course_id",id);
        boolean chapter = eduChapterService.remove(queryWrapper1);
        //3.删除课程描述CourseDescription
        boolean description = eduCourseDescriptionService.removeById(id);
        //4.删除课程Course
        int i = baseMapper.deleteById(id);
        boolean course= i>0 ;
        return video && chapter && description && course;
    }

    @Override
    public List<EduCourse> getFrontEightCourse() {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        queryWrapper.last("limit 8");
        List<EduCourse> eduCourseList = baseMapper.selectList(queryWrapper);
        return eduCourseList;
    }

    @Override
    public List<EduCourse> selectCourseByTeacherId(String teacherId) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id",teacherId);
        List<EduCourse> courseList = baseMapper.selectList(queryWrapper);
        return courseList;
    }

    @Override
    public Map<String,Object> pageQueryCourse(Page<EduCourse> pageParam, CourseQueryParam param) {
        String subjectId = param.getSubjectId();
        String subjectParentId = param.getSubjectParentId();
        String gmtCreateSort = param.getGmtCreateSort();
        String buyCountSort = param.getBuyCountSort();
        String priceSort = param.getPriceSort();
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(subjectId)){
            queryWrapper.eq("subject_id",subjectId);
        }
        if (!StringUtils.isEmpty(subjectParentId)){
            queryWrapper.eq("subject_parent_id",subjectParentId);
        }
        if (!StringUtils.isEmpty(gmtCreateSort)){
            queryWrapper.orderByDesc(gmtCreateSort);
        }
        if (!StringUtils.isEmpty(buyCountSort)){
            queryWrapper.orderByDesc(buyCountSort);
        }
        if (!StringUtils.isEmpty(priceSort)){
            queryWrapper.orderByDesc(priceSort);
        }

        baseMapper.selectPage(pageParam,queryWrapper);

        List<EduCourse> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();
        boolean hasPrevious = pageParam.hasPrevious();

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

    @Override
    public CourseWebVo getCourseInfoByCourseId(String courseId) {
        return baseMapper.getCourseInfoByCourseId(courseId);
    }

}
