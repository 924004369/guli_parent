package com.atguigu.oss.controller;

import com.atguigu.commonutils.ResultMap;
import com.atguigu.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author chairuntao
 * @version 1.0
 * @date 2020/5/8 22:18
 */
@RestController
@CrossOrigin
@RequestMapping("/eduoss/fileoss")
public class OssController {
    @Autowired
    OssService ossService;
    @PostMapping("/")
    public ResultMap uploadFile(MultipartFile file){
        String url=ossService.uploadAvatar(file);
         return ResultMap.ok().data("url",url);
    }
}
