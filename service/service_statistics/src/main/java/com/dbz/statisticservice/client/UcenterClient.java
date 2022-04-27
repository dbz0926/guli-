package com.dbz.statisticservice.client;

import com.dbz.commonutils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 10979
 * @description:
 * @date 2022/4/1921:46
 */
@FeignClient(name = "service-ucenter" ,fallback = UcenterClientImpl.class)
@Component
public interface UcenterClient {
    @GetMapping("/ucenterservice/member/countRegister/{day}")
    Result countRegisterByDay(@PathVariable String day);
}
