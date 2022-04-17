package com.mayikt.member.service.api.impl;

import com.mayikt.member.service.api.MemberService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MemberServiceImpl implements MemberService {

    @Value("${server.port}")
    private String serverPort;

    @Override
    public String getUser(Long userId) {
        return "我是会员服务端口号为:" + serverPort;
    }

    @RequestMapping("/")
    public String member(HttpServletRequest request){
        String serverPort = request.getHeader("serverPort");
        return "this is member,网关端口号:"+ serverPort;
    }
}
