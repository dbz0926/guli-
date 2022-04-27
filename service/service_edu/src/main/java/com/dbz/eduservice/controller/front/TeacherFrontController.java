package com.dbz.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dbz.commonutils.Result;
import com.dbz.eduservice.entity.EduCourse;
import com.dbz.eduservice.entity.EduTeacher;
import com.dbz.eduservice.service.EduCourseService;
import com.dbz.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 10979
 * @description:
 * @date 2022/4/1614:54
 */
@RestController
@RequestMapping("eduservice/teacherfront")
public class TeacherFrontController {
    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;

    @GetMapping("getTeacher/{curpage}/{limit}")
    public Result getTeacherByPageQuery(@PathVariable Long curpage,@PathVariable Long limit){
        Page<EduTeacher> teacherPage = new Page<>(curpage, limit);
        Map<String,Object> map=teacherService.getTeacherByPageQuery(teacherPage);
        return Result.ok().data(map);
    }

    @GetMapping("getTeacherInfo/{teacherId}")
    public Result getTeacherInfoByTeacherId(@PathVariable String teacherId){
        EduTeacher teacher = teacherService.getById(teacherId);
        List<EduCourse> courseList=courseService.selectCourseByTeacherId(teacherId);
        return Result.ok().data("teacher",teacher).data("courseList",courseList);
    }

}
