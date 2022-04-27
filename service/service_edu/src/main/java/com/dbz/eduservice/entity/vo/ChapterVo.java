package com.dbz.eduservice.entity.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 10979
 * @description:
 * @date 2022/4/815:20
 */
@ApiModel(value = "章节信息")
@Data
public class ChapterVo {
    private String id;

    private String title;

    private List<VideoVo> children = new ArrayList<>();
}
