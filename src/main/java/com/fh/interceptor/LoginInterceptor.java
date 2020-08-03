package com.fh.interceptor;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.fh.commons.Ignore;
import com.fh.commons.LoginException;
import com.fh.member.model.Member;
import com.fh.utils.JwtUtil;
import com.fh.utils.RedisUtil;
import com.fh.utils.SystemContans;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.net.URLDecoder;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//处理客户端传过来的自定义头信息
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,"x-auth,mtoken,content-type");
//处理客户端发过来的put，delete
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS,"PUT,POST,DELETE,GET");

        HandlerMethod handlerMethod= (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        /*通过注解放过方法拦截 判断注解是否存在*/
        if(method.isAnnotationPresent(Ignore.class)){

            return  true;
        }
        //获取请求头里得token
        String token = request.getHeader("x-auth");
        //如果没有token 返回登录页面
        if(StringUtils.isEmpty(token)){
            throw new LoginException();
        }
        boolean exit=  RedisUtil.exist(SystemContans.TOKEN_KEY+token);
       /* if(!exit){
            //token 失效
            throw  new javax.security.auth.login.LoginException();
        }else {
            RedisUtil.get(token);
        }*/
        //验证token
            boolean res = JwtUtil.verify(token);
            if(res){
            String userString = JwtUtil.getUser(token);
            String jsonUser = URLDecoder.decode(userString, "utf-8");
            //相当于获取user
            Member member = JSONObject.parseObject(jsonUser, Member.class);
            //将当前用户存放到session中以便控制层获取
                request.getSession().setAttribute(SystemContans.SESSION_KEY,member);
               /* request.getSession().setAttribute(SystemContans.TOKEN_KEY,token);
                RedisUtil.set(token,token)*/;


            }else {
            throw new LoginException();
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
