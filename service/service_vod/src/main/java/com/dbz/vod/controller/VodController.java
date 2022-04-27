package com.dbz.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.dbz.commonutils.Result;
import com.dbz.vod.service.VodService;
import com.dbz.vod.utils.AliyunVodSDKUtils;
import com.dbz.vod.utils.ConstantPropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.ValidationEvent;
import java.util.List;

/**
 * @author 10979
 * @description:
 * @date 2022/4/920:56
 */
@RestController
@RequestMapping("eduvod/video")
public class VodController {
    @Autowired
    private VodService vodService;

    @PostMapping("uploadAliyunVideo")
    public Result uploadAliyunVideo(MultipartFile file){
        String videoId=vodService.uploadAliyunVideo(file);
        return Result.ok().data("videoId",videoId);
    }

    @DeleteMapping("{videoSourceId}")
    public Result removeVideoById(@PathVariable String videoSourceId){
        vodService.videoSourceId(videoSourceId);
        return Result.ok().message("删除视频成功");

    }

    @DeleteMapping("removeBatchVideo")
    public Result removeBatchVideo(@RequestParam(value = "videoIds") List videoIds){
        vodService.removeBatchVideo(videoIds);
        return Result.ok();
    }

    @GetMapping("getplayauth/{videoId}")
    public Result getplayauthById(@PathVariable("videoId") String videoId) throws ClientException {
        DefaultAcsClient initVodClient = AliyunVodSDKUtils.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID,
                ConstantPropertiesUtil.ACCESS_KEY_SECRET);
        GetVideoPlayAuthRequest request=new GetVideoPlayAuthRequest();
        request.setVideoId(videoId);
        GetVideoPlayAuthResponse response = initVodClient.getAcsResponse(request);
        String playAuth = response.getPlayAuth();
        return Result.ok().data("playAuth",playAuth);
    }
}
