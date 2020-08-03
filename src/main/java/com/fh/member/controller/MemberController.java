package com.fh.member.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.fh.commons.Ignore;
import com.fh.commons.ServerResponse;
import com.fh.member.model.Member;
import com.fh.member.service.MemberService;
import com.fh.utils.MessageVerifyUtils;
import com.fh.utils.RedisUtil;
import com.fh.utils.SystemContans;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Resource
    private MemberService memberService;
    @Ignore
    @RequestMapping("getUserByName")
    public ServerResponse checkMemberName(String name){
        return memberService.checkMemberName(name);
    }
    @Ignore
    @RequestMapping("getUserByphone")
    public ServerResponse getUserByphone(String phone){
        return memberService.getUserByphone(phone);
    }
    @Ignore
    @RequestMapping("register")
    public ServerResponse register( Member member){
        return memberService.register(member);
    }
    @Ignore
    @RequestMapping("login")
    public ServerResponse login(Member member){
        return memberService.login(member);
    }


    @RequestMapping("checkLogin")
    public ServerResponse checkLogin(HttpServletRequest request){
        Member member = (Member) request.getSession().getAttribute(SystemContans.SESSION_KEY);
          if(member==null){
              return  ServerResponse.loginError();
          }
        return ServerResponse.success();
    }

    @Ignore
    @RequestMapping("out")
     public ServerResponse out(HttpServletRequest request){
        String token = (String) request.getSession().getAttribute(SystemContans.TOKEN_KEY);
        RedisUtil.del(SystemContans.TOKEN_KEY+token);
        return ServerResponse.success();

    }






}
