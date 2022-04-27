package com.dbz.eduservice.controller;


import com.dbz.commonutils.Result;
import com.dbz.eduservice.entity.subject.OneSubject;
import com.dbz.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author dbz
 * @since 2022-04-04
 */
@RestController
@RequestMapping("/eduservice/subject")
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    @PostMapping("addSubject")
    public Result addSubject(MultipartFile file){
        subjectService.saveSubject(file,subjectService);
        return Result.ok();
    }

    @GetMapping("getAllSubject")
    public  Result getAllSubject(){
        List<OneSubject> list=subjectService.getAllOneAndTwoSubject();
        return Result.ok().data("items",list);
    }





}

