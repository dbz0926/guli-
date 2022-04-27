package com.dbz.eduservice.mapper;

import com.dbz.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dbz.eduservice.entity.vo.CoursePublishVo;
import com.dbz.eduservice.entity.vo.frontvo.CourseWebVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author dbz
 * @since 2022-04-05
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    public CoursePublishVo getCoursePublishVo(String courseId);

    CourseWebVo getCourseInfoByCourseId(String courseId);
}
