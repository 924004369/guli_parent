package com.atguigu.serviceedu.controller;

import com.atguigu.commonutils.ResultMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chairuntao
 * @version 1.0
 * @date 2020/5/8 9:12
 */
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {

    @PostMapping("/login")
    public ResultMap login(){
        return ResultMap.ok().data("token","admin");
    }


    @GetMapping("/info")
    public ResultMap info(){
        Map<String , Object> map=new HashMap<>();
        map.put("roles","[admin]");
        map.put("name","admin");
        map.put("avatar","https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/11/07/91871e25-fd83-4af6-845f-ea8d471d825d.png");
        return ResultMap.ok().data(map);
    }
}
