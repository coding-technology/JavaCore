package com.yanqun.micro_city.interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Created by 颜群
 */
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {


    //请求 ->拦截器(pre) -> 增删改
    @Autowired
    private JwtUtil jwtUtil ;

    @Autowired
    private HttpServletRequest request ;



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("jwt拦截器...");
        String authentication = request.getHeader("authentication");
        if(authentication != null && authentication.startsWith("yanqun-")) {
            String token = authentication.substring(7);
            Claims claims = jwtUtil.parseJWT(token);
            String roles = (String)claims.get("roles");
            if(roles.equals("admin")){
                request.setAttribute("claims",claims);
            }
        }
        return true;
    }
}
