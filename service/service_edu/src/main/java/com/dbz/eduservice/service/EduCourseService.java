package com.dbz.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dbz.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dbz.eduservice.entity.vo.CourseInfoVo;
import com.dbz.eduservice.entity.vo.CoursePublishVo;
import com.dbz.eduservice.entity.vo.CourseQuery;
import com.dbz.eduservice.entity.vo.frontvo.CourseQueryParam;
import com.dbz.eduservice.entity.vo.frontvo.CourseWebVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author dbz
 * @since 2022-04-05
 */
public interface EduCourseService extends IService<EduCourse> {

    String addCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfoById(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo getCoursePublishVoByCourseId(String courseId);

    void conditionPageQuery(Page<EduCourse> coursePage, CourseQuery courseQuery);

    boolean removeCourseById(String id);

    List<EduCourse> getFrontEightCourse();

    List<EduCourse> selectCourseByTeacherId(String teacherId);

    Map<String,Object> pageQueryCourse(Page<EduCourse> pageParam, CourseQueryParam param);

    CourseWebVo getCourseInfoByCourseId(String courseId);
}
