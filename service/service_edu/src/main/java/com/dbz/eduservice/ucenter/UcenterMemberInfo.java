package com.dbz.eduservice.ucenter;


import com.dbz.ucenterservice.entity.UcenterMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 10979
 * @description:
 * @date 2022/4/1716:24
 */
@FeignClient(value = "service-ucenter",fallback = com.dbz.eduservice.ucenter.UcenterMemberInfoImpl.class)
@Component
public interface UcenterMemberInfo {
    @GetMapping("/ucenterservice/member/getMemberInfo/{memberId}")
    UcenterMember getMemberInfoByMemberId(@PathVariable String memberId);
}
