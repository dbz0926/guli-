package com.dbz.statisticservice.feign;

import com.dbz.commonutils.vo.UcenterMemberOrder;
import com.dbz.statisticservice.feign.impl.OrderFeignMemberImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 10979
 * @description:
 * @date 2022/4/1722:25
 */
@FeignClient(name = "service-ucenter" ,fallback = OrderFeignMemberImpl.class)
@Component
public interface OrderFeignMember {
    @GetMapping("/ucenterservice/member/getMemberInfoOrder/{memberId}")
    public UcenterMemberOrder getMemberInfoOrderByMemberId(@PathVariable String memberId);
}
