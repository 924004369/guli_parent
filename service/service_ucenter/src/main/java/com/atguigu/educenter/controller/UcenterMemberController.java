package com.atguigu.educenter.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.ResultMap;
import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.atguigu.educenter.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author chairuntao
 * @since 2020-05-28
 */
@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService ucenterMemberService;

    @PostMapping("/login")
    public ResultMap loginUser(@RequestBody UcenterMember member){
        String token=ucenterMemberService.login(member);
        return ResultMap.ok().data("token",token);
    }


    //注册
    @PostMapping("/register")
    public ResultMap registerUser(@RequestBody RegisterVo registerVo){
        ucenterMemberService.registerUser(registerVo);
        return ResultMap.ok();
    }

    //根据token获取用户信息
    @GetMapping(value = "/getMemberInfo")
    public ResultMap getMemberInfo(HttpServletRequest request){
        //根据jwtUtils工具得到里面的用户ID
        final String memberId = JwtUtils.getMemberIdByJwtToken(request);
        final UcenterMember member = ucenterMemberService.getById(memberId);
        return ResultMap.ok().data("userInfo",member);
    }
}

