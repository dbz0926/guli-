package com.dbz.eduservice.controller.front;

import com.dbz.commonutils.Result;
import com.dbz.eduservice.entity.EduCourse;
import com.dbz.eduservice.entity.EduTeacher;
import com.dbz.eduservice.service.EduCourseService;
import com.dbz.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 10979
 * @description:
 * @date 2022/4/1319:53
 */
@RestController
@RequestMapping("eduservice/indexFront")
public class IndexFrontController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @Autowired
    private EduCourseService eduCourseService;

    @GetMapping("indexFrontHotTeacherAndCourse")
    public Result indexFrontHotTeacherAndCourse(){
        List<EduCourse> eduCourseList=eduCourseService.getFrontEightCourse();
        List<EduTeacher> eduTeacherList=eduTeacherService.getFrontEightTeacher();
        return Result.ok().data("courseList",eduCourseList).data("teacherList",eduTeacherList);
    }
}
