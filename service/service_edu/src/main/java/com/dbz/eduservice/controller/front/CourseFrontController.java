package com.dbz.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dbz.commonutils.JwtUtils;
import com.dbz.commonutils.Result;
import com.dbz.commonutils.vo.CourseWebOrder;
import com.dbz.eduservice.client.OrderClient;
import com.dbz.eduservice.entity.EduCourse;
import com.dbz.eduservice.entity.vo.ChapterVo;
import com.dbz.eduservice.entity.vo.frontvo.CourseQueryParam;
import com.dbz.eduservice.entity.vo.frontvo.CourseWebVo;
import com.dbz.eduservice.service.EduChapterService;
import com.dbz.eduservice.service.EduCourseService;
import com.dbz.servicebase.handler.GuliException;
import io.swagger.annotations.ApiModelProperty;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author 10979
 * @description:
 * @date 2022/4/1616:31
 */
@RestController
@RequestMapping("eduservice/course")
public class CourseFrontController {
    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;

    @Autowired
    private OrderClient orderClient;

    @PostMapping("pageQuery/{curpage}/{limit}")
    public Result pageQueryCourse(@PathVariable Long curpage,
                                  @PathVariable Long limit,
                                  @RequestBody(required = false) CourseQueryParam param){
        Page<EduCourse> coursePage = new Page<>(curpage, limit);
        Map<String,Object> map = courseService.pageQueryCourse(coursePage,param);
        return  Result.ok().data(map);
    }

    @GetMapping("getFrontCourseInfo/{courseId}")
    public Result getCourseInfoByCourseId(@PathVariable String courseId, HttpServletRequest request){
        CourseWebVo courseWebVo = courseService.getCourseInfoByCourseId(courseId);
        List<ChapterVo> chapterAndVideo = chapterService.getChapterVideoByCourseId(courseId);
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if (StringUtils.isEmpty(memberId)){
            throw new GuliException(20001,"请先登录账号");
        }
        boolean buyCourse = orderClient.isBuyCourse(memberId, courseId);
        return Result.ok().data("course",courseWebVo).data("chapterVoList",chapterAndVideo)
                .data("isbuy",buyCourse);
    }

    @GetMapping("getCourseInfoOrder/{courseId}")
    public CourseWebOrder getCourseInfoOrder(@PathVariable String courseId){
        CourseWebVo courseWebVo = courseService.getCourseInfoByCourseId(courseId);
        CourseWebOrder courseWebOrder = new CourseWebOrder();
        BeanUtils.copyProperties(courseWebVo,courseWebOrder);
        return courseWebOrder;
    }
}
