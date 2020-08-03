package com.fh.member.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.commons.ServerResponse;
import com.fh.member.mapper.MemberMapper;
import com.fh.member.model.Member;
import com.fh.utils.JwtUtil;
import com.fh.utils.RedisUtil;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@Service
public class MemberServiceImpl implements MemberService {
   @Resource
    private MemberMapper memberMapper;

    @Override
    public ServerResponse checkMemberName(String name) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
         queryWrapper.eq("name",name);
        Member member=memberMapper.selectOne(queryWrapper);
        if(member == null){
          return   ServerResponse.success();
        }

        return ServerResponse.error("用户名已存在");
    }

    @Override
    public ServerResponse getUserByphone(String phone) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone",phone);
        Member member=memberMapper.selectOne(queryWrapper);
        if(member == null){
            return   ServerResponse.success();
        }

        return ServerResponse.error("手机号已存在");
    }
    @Override
    public ServerResponse register(Member member) {
        String redisCode = RedisUtil.get(member.getPhone());
        if(redisCode==null){
            return  ServerResponse.error("验证码已失效！");
        }
        if(!redisCode.equals(member.getCode())){
            return ServerResponse.error("验证码错误");
        }
        memberMapper.insert(member);
        return ServerResponse.success();
    }

    @Override
    public ServerResponse login(Member member) {

        QueryWrapper<Member> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name",member.getName());
        queryWrapper.or();
        queryWrapper.eq("phone",member.getPhone());
        Member memberDB = memberMapper.selectOne(queryWrapper);

        ////判断用户或者手机是否存在
        if(member == null){
           return ServerResponse.error("用户名或者手机不存在");
        }
        if(!member.getPassword().equals(memberDB.getPassword())){
            return ServerResponse.error("密码错误");
        }
         //账号密码正确 生成token
        String token="";
        try {
            String jsonString = JSONObject.toJSONString(memberDB);
            String encode= URLEncoder.encode(jsonString, "utf-8");
            token = JwtUtil.sign(encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        return ServerResponse.success(token);
    }
}
