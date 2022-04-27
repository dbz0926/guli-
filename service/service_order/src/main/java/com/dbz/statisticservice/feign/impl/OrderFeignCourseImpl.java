package com.dbz.statisticservice.feign.impl;

import com.dbz.commonutils.vo.CourseWebOrder;
import com.dbz.statisticservice.feign.OrderFeignCourse;
import org.springframework.stereotype.Component;

/**
 * @author 10979
 * @description:
 * @date 2022/4/1722:32
 */
@Component
public class OrderFeignCourseImpl implements OrderFeignCourse {
    @Override
    public CourseWebOrder getCourseInfoOrder(String courseId) {
        return null;
    }
}
