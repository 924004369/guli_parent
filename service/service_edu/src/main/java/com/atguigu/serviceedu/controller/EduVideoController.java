package com.atguigu.serviceedu.controller;


import com.atguigu.commonutils.ResultMap;
import com.atguigu.serviceedu.entity.EduVideo;
import com.atguigu.serviceedu.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
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
     * TODO 删除小节的时候也要删除里面对应的视频
     * @param
     * @return
     */
    @DeleteMapping("/deleteVideoByid/{id}")
    public ResultMap deleteVideoById(@PathVariable String id){
        boolean flag=videoService.deleteVideoById(id);
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

