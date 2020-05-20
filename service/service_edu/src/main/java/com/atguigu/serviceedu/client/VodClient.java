package com.atguigu.serviceedu.client;

import com.atguigu.commonutils.ResultMap;
import com.atguigu.serviceedu.client.impl.VodClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author chairuntao
 * @version 1.0
 * @date 2020/5/14 17:09
 */
@Component
@FeignClient(name = "service-vod",fallback = VodClientImpl.class)
public interface VodClient {

    //根据视频id删除阿里云视频
    @DeleteMapping("/eduvod/video/removeAlyVideo/{id}")
    public ResultMap removeAlyVideo(@PathVariable("id") String id);

    //删除多个阿里云视频的方法
    //参数多个视频id  List videoIdList
    @DeleteMapping("/delete-batch")
    public ResultMap deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);
}
