package com.atguigu.msmservice.controller;

import com.atguigu.commonutils.ResultMap;
import com.atguigu.msmservice.service.MsmService;
import com.atguigu.msmservice.utils.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author chairuntao
 * @version 1.0
 * @date 2020/5/24 9:22
 */
@RestController
@RequestMapping(value = "/edumsm/msm")
@CrossOrigin
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    @GetMapping("/send/{phone}")
    public ResultMap sendMsm(@PathVariable(value = "phone")String phone){
        //1.从redis获取验证码，如果获取到直接返回
        final String code1 = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code1)){
            return ResultMap.ok();
        }
        //2.如果redis获取不到，进行阿里云发送

        //生成一个验证码，传递阿里云进行发送
        String code= RandomUtil.getSixBitRandom();

        Map<String,Object> map=new HashMap<>();
        map.put("code",code);
        Boolean isSend=msmService.send(map,phone);
        if (isSend){
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return ResultMap.ok();
        }
        return ResultMap.error().message("短信发送失败");
    }
}
