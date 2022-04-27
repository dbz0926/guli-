package com.dbz.eduservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 10979
 * @description:
 * @date 2022/4/1919:56
 */
@Component
@FeignClient(name = "service-order" ,fallback = OrderClientImpl.class)
public interface OrderClient {
    @GetMapping("/orderservice/order/isBuyCourse/{memberid}/{courseId}")
    boolean isBuyCourse(@PathVariable String memberid, @PathVariable String courseId);
}
