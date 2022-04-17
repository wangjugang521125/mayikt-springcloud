package com.mayikt.order.service.api.impl;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.mayikt.order.service.api.openFeign.MemberServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("all")
public class OrderService {

    private static final String GETORDER_KEY = "orderToMember";

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

    @RequestMapping("/orderToMember")
    public String orderToMember(){
        Entry entry = null;
        try {
            entry = SphU.entry(GETORDER_KEY);
            // 执行我们服务需要保护的业务逻辑
            return "orderToMember接口";
        } catch (Exception e) {
            e.printStackTrace();
            return "该服务接口已经达到上线!";
        } finally {
            // SphU.entry(xxx) 需要与 entry.exit() 成对出现,否则会导致调用链记录异常
            if (entry != null) {
                entry.exit();
            }
        }
    }

    /**
     * fallback  服务降级执行的本地方法
     * blockHandler  限流/熔断出现异常执行的方法
     * value   指定我们资源的名称
     * @return
     */
    @SentinelResource(value = GETORDER_KEY, blockHandler = "getOrderQpsException")
    @RequestMapping("/getOrderAnnotation")
    public String getOrderAnnotation() {
        return "getOrder接口";
    }


    /**
     * 被限流后返回的提示
     *
     * @param e
     * @return
     */
    public String getOrderQpsException(BlockException e) {
        e.printStackTrace();
        return "该接口已经被限流啦!";
    }



    @SentinelResource(value = "getOrderSentinel", blockHandler = "getOrderQpsException")
    @RequestMapping("/getOrderSentinel")
    public String getOrderSentinel() {
        return "getOrderSentinel";
    }

}
