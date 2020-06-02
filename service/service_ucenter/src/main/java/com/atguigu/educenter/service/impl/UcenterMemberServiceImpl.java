package com.atguigu.educenter.service.impl;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.MD5;
import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.atguigu.educenter.mapper.UcenterMemberMapper;
import com.atguigu.educenter.service.UcenterMemberService;
import com.atguigu.servciebase.config.exceptionHandle.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author chairuntao
 * @since 2020-05-28
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    //注册
    @Override
    public void registerUser(RegisterVo registerVo) {
        final String code = registerVo.getCode();
        final String mobile = registerVo.getMobile();
        final String nickname = registerVo.getNickname();
        final String password = registerVo.getPassword();

        if (StringUtils.isEmpty(code)) {
            throw new GuliException(400, "验证码不能为空");
        }

        if (StringUtils.isEmpty(mobile)) {
            throw new GuliException(400, "手机号不能为空");
        }

        if (StringUtils.isEmpty(nickname)) {
            throw new GuliException(400, "用户名不能为空");
        }

        if (StringUtils.isEmpty(password)) {
            throw new GuliException(400, "密码不能为空");
        }

        String redisCode = redisTemplate.opsForValue().get(mobile);
        if (!Objects.equals(code, redisCode)) {
            throw new GuliException(400, "注册失败，验证码错误");
        }

        //判断手机号是否重复，表里面有的手机号码不能再次添加
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        final Integer count = baseMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new GuliException(400, "注册失败，手机号已被注册");
        }

        UcenterMember ucenterMember = new UcenterMember();
        ucenterMember.setMobile(registerVo.getMobile());
        ucenterMember.setNickname(registerVo.getNickname());
        ucenterMember.setPassword(MD5.encrypt(registerVo.getPassword()));
        ucenterMember.setIsDeleted(false);//用户不禁用
        ucenterMember.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        final int insert = baseMapper.insert(ucenterMember);
    }

    @Override
    public String login(UcenterMember member) {
        String mobile = member.getMobile();
        String password = member.getPassword();
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new GuliException(400, "手机号码或者密码为空");
        }

        //判断手机号是否正确
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        final UcenterMember mobileMember = baseMapper.selectOne(queryWrapper);

        if (mobileMember == null) {
            throw new GuliException(400, "手机号不存在");
        }

        //判断密码
        final String encrypt = MD5.encrypt(password);
        if (!encrypt.equals(mobileMember.getPassword())) {
            throw new GuliException(400, "密码错误");
        }

        //判断用户是否被禁用
        if (mobileMember.getIsDisabled()) {
            throw new GuliException(400, "用户被禁用");
        }

        //登陆成功
        final String jwtToken = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());

        return jwtToken;
    }
}
