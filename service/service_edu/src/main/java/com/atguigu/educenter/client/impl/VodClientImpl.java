package com.atguigu.educenter.client.impl;

import com.atguigu.commonutils.ResultMap;
import com.atguigu.educenter.client.VodClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chairuntao
 * @version 1.0
 * @date 2020/5/15 11:09
 */
@Component
public class VodClientImpl implements VodClient {
    @Override
    public ResultMap removeAlyVideo(String id) {
        return ResultMap.error().message("删除视频出错");
    }

    @Override
    public ResultMap deleteBatch(List<String> videoIdList) {
        return ResultMap.error().message("删除多个视频出错");
    }
}
