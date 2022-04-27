package com.dbz.statisticservice.feign;

import com.dbz.commonutils.vo.CourseWebOrder;
import com.dbz.statisticservice.feign.impl.OrderFeignCourseImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 10979
 * @description:
 * @date 2022/4/1722:30
 */
@FeignClient(name = "service-edu",fallback = OrderFeignCourseImpl.class)
@Component
public interface OrderFeignCourse {
    @GetMapping("eduservice/course/getCourseInfoOrder/{courseId}")
    public CourseWebOrder getCourseInfoOrder(@PathVariable String courseId);
}
