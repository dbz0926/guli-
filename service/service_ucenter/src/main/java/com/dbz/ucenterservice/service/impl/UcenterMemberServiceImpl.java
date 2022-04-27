package com.dbz.ucenterservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dbz.commonutils.JwtUtils;
import com.dbz.commonutils.MD5;
import com.dbz.servicebase.handler.GuliException;
import com.dbz.ucenterservice.entity.UcenterMember;
import com.dbz.ucenterservice.entity.vo.RegisterVo;
import com.dbz.ucenterservice.mapper.UcenterMemberMapper;
import com.dbz.ucenterservice.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author dbz
 * @since 2022-04-14
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public String login(UcenterMember ucenterMember) {
        String mobilePhone = ucenterMember.getMobile();
        String password = ucenterMember.getPassword();

        if (StringUtils.isEmpty(mobilePhone) || StringUtils.isEmpty(password)){
            throw new GuliException(20001,"手机号错误或者密码错误");
        }
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobilePhone);
        UcenterMember mobileMember = baseMapper.selectOne(queryWrapper);

        if (mobileMember==null) {
            throw new GuliException(20001,"手机号未注册");
        }

        String encryptPassword=MD5.encrypt(password);
        if (!encryptPassword.equals(mobileMember.getPassword())){
            throw new GuliException(20001,"密码错误");
        }

        String jwtToken = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());
        return jwtToken;
    }

    /**
     * description:注册
     * @param registerVo
     */
    @Override
    public void register(RegisterVo registerVo) {
        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String nickname = registerVo.getNickname();

        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(password) ||StringUtils.isEmpty(nickname)){
            throw new GuliException(20001,"注册失败，请输入正确的信息");
        }

        if (!code.equals(redisTemplate.opsForValue().get(mobile))){
            throw new GuliException(20001,"验证码错误");
        }

        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count>0){
            throw new GuliException(20001,"此手机号已被注册，请勿重复注册");
        }

        String encryptPassword = MD5.encrypt(password);

        UcenterMember ucenterMember = new UcenterMember();
        BeanUtils.copyProperties(registerVo,ucenterMember);
        ucenterMember.setPassword(encryptPassword);
        ucenterMember.setAvatar("http://myguli-avatar.oss-cn-chengdu.aliyuncs.com/2022/04/04/e4d8a665-b7a6-4b61-93e1-3a6083159b1c.png");
        baseMapper.insert(ucenterMember);
    }

    @Override
    public UcenterMember getByOpenid(String openid) {
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid", openid);
        UcenterMember ucenterMember = baseMapper.selectOne(queryWrapper);
        return ucenterMember;
    }

    @Override
    public int countRegisterByDay(String day) {
        return baseMapper.countRegisterByDay(day);
    }
}
