package com.atguigu.vodtest;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;

import java.util.List;

/**
 * @author chairuntao
 * @version 1.0
 * @date 2020/5/13 8:14
 */
public class TestVod {
    public static void main(String[] args) throws ClientException {
        final DefaultAcsClient client = main.initVodClient("LTAI4FyNVxZvFtoTh2DxeA3g", "DQJXv7almyomAzGDNrnJrXBIVgXsus");

        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();

        request.setVideoId("c7f8b4904bb94bb6bef7b2df2cfbea5d");


        response=client.getAcsResponse(request);


        final List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        for (GetPlayInfoResponse.PlayInfo playInfo:playInfoList){
            System.out.println("PlayInfo.playUrl:="+playInfo.getPlayURL()+"/n");

            System.out.println("VideoBase.Title = "+response.getVideoBase().getTitle()+"/n");
        }
    }
}
