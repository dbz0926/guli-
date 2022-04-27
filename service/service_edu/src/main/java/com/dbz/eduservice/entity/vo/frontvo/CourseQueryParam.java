package com.dbz.eduservice.entity.vo.frontvo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 10979
 * @description:
 * @date 2022/4/1616:29
 */
@Data
public class CourseQueryParam {
    @ApiModelProperty(value = "课程名称")
    private String title;

    @ApiModelProperty(value = "讲师id")
    private String teacherId;

    @ApiModelProperty(value = "一级类别id")
    private String subjectParentId;

    @ApiModelProperty(value = "二级类别id")
    private String subjectId;

    @ApiModelProperty(value = "销量排序")
    private String buyCountSort;

    @ApiModelProperty(value = "最新时间排序")
    private String gmtCreateSort;

    @ApiModelProperty(value = "价格排序")
    private String priceSort;
}
