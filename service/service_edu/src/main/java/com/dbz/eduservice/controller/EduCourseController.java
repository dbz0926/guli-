package com.dbz.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dbz.commonutils.Result;
import com.dbz.eduservice.entity.EduCourse;
import com.dbz.eduservice.entity.vo.CourseInfoVo;
import com.dbz.eduservice.entity.vo.CoursePublishVo;
import com.dbz.eduservice.entity.vo.CourseQuery;
import com.dbz.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author dbz
 * @since 2022-04-05
 */
@RestController
@RequestMapping("/eduservice/course")
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    @PostMapping("addCourseInfo")
    public Result addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id=eduCourseService.addCourseInfo(courseInfoVo);
        return Result.ok().data("courseId",id);
    }

    @GetMapping("getCourseInfo/{courseId}")
    public Result getCourseInfoByCourseId(@PathVariable String courseId){
        CourseInfoVo courseInfoVo=eduCourseService.getCourseInfoById(courseId);
        return Result.ok().data("item",courseInfoVo);
    }

    @PostMapping("updateCourseInfo")
    public Result updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        eduCourseService.updateCourseInfo(courseInfoVo);
        return Result.ok().data("courseId",courseInfoVo.getId());
    }

    @GetMapping("getCoursePublishVo/{courseId}")
    public Result getCoursePublishVoByCourseId(@PathVariable String courseId){
        CoursePublishVo coursePublishVo=eduCourseService.getCoursePublishVoByCourseId(courseId);
        return Result.ok().data("item",coursePublishVo);
    }

    @PutMapping("updateCourseStatus/{id}")
    public Result updateCourseStatusById(@PathVariable String id){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        eduCourseService.updateById(eduCourse);
        return Result.ok();
    }

    @GetMapping("{page}/{limit}")
    public Result conditionPageQuery(
            @PathVariable Long page,
            @PathVariable Long limit,
            CourseQuery courseQuery){
        Page<EduCourse> coursePage = new Page<>(page, limit);
        eduCourseService.conditionPageQuery(coursePage,courseQuery);
        List<EduCourse> records = coursePage.getRecords();
        long total = coursePage.getTotal();
        return Result.ok().data("rows",records).data("total",total);
    }

    @DeleteMapping("{id}")
    public Result removeCourseById(@PathVariable String id){
        boolean isRemove=eduCourseService.removeCourseById(id);
        if (isRemove) {
            return Result.ok();
        }else {
            return Result.error();
        }
    }

}

