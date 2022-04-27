package com.dbz.ucenterservice.mapper;

import com.dbz.ucenterservice.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author dbz
 * @since 2022-04-14
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    int countRegisterByDay(String day);
}
