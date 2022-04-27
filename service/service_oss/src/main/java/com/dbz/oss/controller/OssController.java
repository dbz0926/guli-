package com.dbz.oss.controller;

import com.dbz.commonutils.Result;
import com.dbz.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.format.DecimalStyle;

/**
 * @author 10979
 * @description:
 * @date 2022/4/415:21
 */
@Api(description = "上传文件")
@RestController
@RequestMapping("/eduoss/fileoss")
public class OssController {
    @Autowired
    private OssService ossService;
    @PostMapping
    public Result uploadOssFile(MultipartFile file){
        String url= ossService.uploadFileAvatar(file);
        return Result.ok().data("url",url);
    }
}
