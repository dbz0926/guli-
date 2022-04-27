package com.dbz.eduservice.entity.vo;

import lombok.Data;

/**
 * @author 10979
 * @description:
 * @date 2022/4/913:47
 */
@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
