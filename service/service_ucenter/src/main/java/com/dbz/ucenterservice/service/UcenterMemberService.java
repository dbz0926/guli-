package com.dbz.ucenterservice.service;

import com.dbz.ucenterservice.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dbz.ucenterservice.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author dbz
 * @since 2022-04-14
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember ucenterMember);

    void register(RegisterVo registerVo);

    UcenterMember getByOpenid(String openid);

    int countRegisterByDay(String day);
}
