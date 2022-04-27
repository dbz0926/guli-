package com.dbz.msmservice.controller;

import com.dbz.commonutils.Result;
import com.dbz.msmservice.service.MsmService;
import com.dbz.msmservice.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 10979
 * @description:
 * @date 2022/4/1416:23
 */
@RestController
@RequestMapping("msmservice/msm")
public class MsmApiController {
    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping(value = "/send/{phone}")
    public Result code(@PathVariable String phone) {
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)) return Result.ok();

//        code = RandomUtil.getFourBitRandom(); //生成验证码
//        Map<String,Object> param = new HashMap<>();
//        param.put("code", code);
//        String templateCode="SMS_180051135";//模板号
//        boolean isSend = msmService.send(phone, templateCode, param);
//        if(isSend) {
//            redisTemplate.opsForValue().set(phone, code,5, TimeUnit.MINUTES);
//            return Result.ok();
//        } else {
//            return Result.error().message("发送短信失败");
//        }
        code="1234";
        redisTemplate.opsForValue().set(phone, code,5, TimeUnit.MINUTES);
        return Result.ok();
    }
}
