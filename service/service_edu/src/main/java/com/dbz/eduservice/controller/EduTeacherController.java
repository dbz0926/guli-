package com.dbz.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dbz.commonutils.Result;
import com.dbz.eduservice.entity.EduTeacher;
import com.dbz.eduservice.entity.vo.TeacherQuery;
import com.dbz.eduservice.service.EduTeacherService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dbz
 * @since 2022-04-01
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public Result findAllTeacher(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return Result.ok().data("items",list);
    }

    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public Result removeTeacherById(@PathVariable("id") String id){
        boolean b = eduTeacherService.removeById(id);
        if (b){
            return Result.ok();
        }
        return Result.error();
    }

    @ApiOperation(value = "分页查询讲师列表")
    @GetMapping("pageTeacher/{cur}/{limit}")
    public Result pageTeacher(@PathVariable long cur,@PathVariable long limit){
        Page teacherPage = new Page(cur,limit);
        eduTeacherService.page(teacherPage, null);
        long total = teacherPage.getTotal();
        List<EduTeacher> records = teacherPage.getRecords();
        return Result.ok().data("total",total).data("rows",records);
    }

    @ApiOperation(value = "条件分页查询讲师列表")
    @PostMapping("pageTeacherCondition/{cur}/{limit}")
    public Result pageTeacher(@PathVariable Long cur,@PathVariable Long limit,
                              @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<EduTeacher> eduTeacherPage = new Page<>(cur,limit);
        eduTeacherService.queryPageTeacher(eduTeacherPage,teacherQuery);
        long total = eduTeacherPage.getTotal();
        List<EduTeacher> records = eduTeacherPage.getRecords();
        return Result.ok().data("total",total).data("rows",records);
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping("addTeacher")
    public Result insertTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if (save){
            return Result.ok();
        }
            return Result.error();
    }

    @ApiOperation(value = "查询指定id讲师")
    @GetMapping("getTeacher/{id}")
    public Result findTeacherById(@PathVariable String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return Result.ok().data("item",eduTeacher);
    }

    @ApiOperation(value = "修改指定id讲师")
    @PutMapping("updateTeacher/{id}")
    public Result updateTeacherById(@PathVariable String id,@RequestBody(required = false) EduTeacher eduTeacher){
        eduTeacher.setId(id);
        boolean b = eduTeacherService.updateById(eduTeacher);
        if (b) return Result.ok();
        return Result.error();
    }
}

