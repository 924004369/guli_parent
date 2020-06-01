package com.atguigu.educenter.controller;


import com.atguigu.commonutils.ResultMap;
import com.atguigu.servciebase.config.exceptionHandle.GuliException;
import com.atguigu.educenter.client.VodClient;
import com.atguigu.educenter.entity.EduVideo;
import com.atguigu.educenter.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author chairuntao
 * @since 2020-05-11
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    EduVideoService videoService;

    @Autowired
    VodClient vodClient;

    //添加小节
    @PostMapping("/addVideo")
    public ResultMap addVideo(@RequestBody EduVideo video){
        final boolean flag = videoService.save(video);
        if (flag){
            return ResultMap.ok();
        }
        return ResultMap.error();
    }

    //修改小节
    @PutMapping("/update")
    public ResultMap updateVideo(@RequestBody EduVideo eduVideo){
        final boolean b = videoService.updateById(eduVideo);
        if (b){
            return ResultMap.ok();
        }
        return ResultMap.error();
    }

    //删除小节
    /**
     *
     * @param
     * @return
     */
    @DeleteMapping("/deleteVideoByid/{id}")
    public ResultMap deleteVideoById(@PathVariable String id){
        final EduVideo video = videoService.getVideoById(id);
        boolean flag=videoService.deleteVideoById(id);

        if (!StringUtils.isEmpty(video.getVideoSourceId())){
            final ResultMap resultMap = vodClient.removeAlyVideo(video.getVideoSourceId());
            if (resultMap.getCode()==400){
                throw new GuliException(400,"删除视频失败，熔断器。。。");
            }
        }
        if (flag){
            return ResultMap.ok();
        }
        return ResultMap.error();
    }

    //小节数据回显
    @GetMapping("/getVideoById/{id}")
    public ResultMap getVideoById(@PathVariable String id){
        EduVideo eduVideo=videoService.getVideoById(id);
        return ResultMap.ok().data("eduVideo",eduVideo);
    }


}

