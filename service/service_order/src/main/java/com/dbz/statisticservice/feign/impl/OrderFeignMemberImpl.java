package com.dbz.statisticservice.feign.impl;

import com.dbz.commonutils.vo.UcenterMemberOrder;
import com.dbz.statisticservice.feign.OrderFeignMember;
import org.springframework.stereotype.Component;

/**
 * @author 10979
 * @description:
 * @date 2022/4/1722:26
 */
@Component
public class OrderFeignMemberImpl implements OrderFeignMember {
    @Override
    public UcenterMemberOrder getMemberInfoOrderByMemberId(String memberId) {
        return null;
    }
}
