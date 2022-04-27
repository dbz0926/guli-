package com.dbz.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author 10979
 * @description:
 * @date 2022/4/815:21
 */
@ApiModel(value = "课时信息")
@Data
public class VideoVo {

    private String id;

    private String title;

    private Boolean free;

    private String videoSourceId;
}
