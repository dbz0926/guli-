package com.dbz.cmsservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dbz.cmsservice.entity.CrmBanner;
import com.dbz.cmsservice.mapper.CrmBannerMapper;
import com.dbz.cmsservice.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author dbz
 * @since 2022-04-13
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Cacheable(value = "banner",key = "'getAllBanner'")
    @Override
    public List<CrmBanner> getAllBanner() {
        QueryWrapper<CrmBanner> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        queryWrapper.last("limit 2");
        List<CrmBanner> list = baseMapper.selectList(queryWrapper);
        return list;
    }
}
