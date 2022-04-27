package com.dbz.eduservice.controller;


import com.dbz.commonutils.Result;
import com.dbz.eduservice.entity.EduChapter;
import com.dbz.eduservice.entity.vo.ChapterVo;
import com.dbz.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/eduservice/chapter")
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;

    @GetMapping("getChapterVideo/{courseId}")
    public Result getChapterVideoByCourseId(@PathVariable String courseId){
        List<ChapterVo> list =eduChapterService.getChapterVideoByCourseId(courseId);
        return Result.ok().data("items",list);
    }

    @PostMapping("addChapter")
    public Result addChapter(@RequestBody EduChapter eduChapter){
        boolean save = eduChapterService.save(eduChapter);
        if (save) {
            return Result.ok();
        }else {
            return Result.error();
        }

    }

    @PutMapping("updateChapterById")
    public Result updateChapterById(@RequestBody EduChapter eduChapter){
        eduChapterService.updateById(eduChapter);
        return Result.ok();
    }

    @GetMapping("getChapterById/{id}")
    public Result getChapterById(@PathVariable String id){
        EduChapter eduChapter = eduChapterService.getById(id);
        return Result.ok().data("item",eduChapter);
    }

    @DeleteMapping("removeChapterById/{id}")
    public Result removeChapterById(@PathVariable String id){
        boolean flag=eduChapterService.removeChapterById(id);
        if (flag) return Result.ok();
        return Result.error();
    }

}

