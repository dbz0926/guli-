package com.dbz.eduservice.client;

import com.dbz.commonutils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 10979
 * @description:
 * @date 2022/4/1121:56
 */
@FeignClient(name = "service-vod",fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {
    @DeleteMapping("eduvod/video/{videoSourceId}")
     Result removeVideoById(@PathVariable(value = "videoSourceId") String videoSourceId);

    @DeleteMapping("eduvod/video/removeBatchVideo")
     Result removeBatchVideo(@RequestParam(value = "videoIds") List<String> videoIds);
}
