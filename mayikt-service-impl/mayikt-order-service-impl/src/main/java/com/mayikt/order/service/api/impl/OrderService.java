package com.mayikt.order.service.api.impl;

import com.mayikt.order.service.api.openFeign.MemberServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderService {

    @Autowired
    private MemberServiceFeign memberServiceFeign;

    /***
     * 基于feign客户端形式实现RPC远程调用
     * @return
     */
    @RequestMapping("/orderFeignToMember")
    public String orderFeignToMember(){
        String result = memberServiceFeign.getUser(10l);
        return "我是订单服务调用会员服务的接口"+result;
    }


}
