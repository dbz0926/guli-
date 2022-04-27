package com.dbz.eduservice.client;

import com.dbz.commonutils.Result;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 10979
 * @description:
 * @date 2022/4/1222:21
 */
@Component
public class VodFileDegradeFeignClient implements VodClient{
    @Override
    public Result removeVideoById(String videoSourceId) {
        return Result.error().message("time out");
    }

    @Override
    public Result removeBatchVideo(List<String> videoIds) {
        return Result.error().message("time out");
    }
}
