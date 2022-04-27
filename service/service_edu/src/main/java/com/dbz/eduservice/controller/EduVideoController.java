package com.dbz.eduservice.controller;


import com.dbz.commonutils.Result;
import com.dbz.eduservice.client.VodClient;
import com.dbz.eduservice.entity.EduVideo;
import com.dbz.eduservice.entity.vo.VideoVo;
import com.dbz.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author dbz
 * @since 2022-04-05
 */
@RestController
@RequestMapping("/eduservice/video")
public class EduVideoController {
    @Autowired
    private EduVideoService eduVideoService;

    @PostMapping("addVideo")
    public Result addVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.save(eduVideo);
        return Result.ok();
    }
    @Autowired
    private VodClient vodClient;
    @DeleteMapping("removeVideo/{videoId}")
    public Result removeVideoById(@PathVariable String videoId){
        EduVideo eduVideo = eduVideoService.getById(videoId);
        String videoSourceId = eduVideo.getVideoSourceId();
        if (!StringUtils.isEmpty(videoSourceId)){
            vodClient.removeVideoById(videoSourceId);
        }
        eduVideoService.removeById(videoId);
        return Result.ok();
    }

    @PutMapping("updateVideo")
    public Result updateVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.updateById(eduVideo);
        return Result.ok();
    }

    @GetMapping("getVideo/{videoId}")
    public Result getVideoById(@PathVariable String videoId){
        EduVideo eduVideo = eduVideoService.getById(videoId);
        return Result.ok().data("item",eduVideo);
    }
}

