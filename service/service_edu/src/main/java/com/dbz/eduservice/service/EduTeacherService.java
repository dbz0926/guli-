package com.dbz.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dbz.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dbz.eduservice.entity.vo.TeacherQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dbz
 * @since 2022-04-01
 */
public interface EduTeacherService extends IService<EduTeacher> {

    void queryPageTeacher(Page<EduTeacher> eduTeacherPage, TeacherQuery teacherQuery);

    List<EduTeacher> getFrontEightTeacher();

    Map<String, Object> getTeacherByPageQuery(Page<EduTeacher> teacherPage);
}
