package com.atguigu.msmservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.atguigu.msmservice.service.MsmService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author chairuntao
 * @version 1.0
 * @date 2020/5/24 9:24
 */
@Service
public class MsmServiceImpl implements MsmService {
    @Override
    public Boolean send(Map<String, Object> param, String phone) {
        if (StringUtils.isEmpty(phone))
            return false;
        DefaultProfile profile = DefaultProfile.getProfile("default", "LTAI4FyNVxZvFtoTh2DxeA3g", "DQJXv7almyomAzGDNrnJrXBIVgXsus");
        DefaultAcsClient client = new DefaultAcsClient(profile);

        //设置参数
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");


        //设置发送相关的参数
        request.putQueryParameter("PhoneNumbers", phone);//手机号码
        request.putQueryParameter("SignName", "我的万达在线教育网站");//阿里云签名管理
        request.putQueryParameter("TemplateCode", "SMS_190794233");//阿里云模板管理
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));//验证码数据，转换成json数据


        //最终发送
        try {
            CommonResponse response = client.getCommonResponse(request);
            boolean success = response.getHttpResponse().isSuccess();
            return success;
        } catch (ClientException e) {
            e.printStackTrace();
            return false;
        }

    }
}
