package com.dbz.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 10979
 * @description:
 * @date 2022/4/920:57
 */
public interface VodService {
    String uploadAliyunVideo(MultipartFile file);

    void videoSourceId(String videoSourceId);

    void removeBatchVideo(List videoIds);
}
