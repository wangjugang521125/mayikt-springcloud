package com.mayikt.member.service.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface MemberService {

    /**
     * 提供会员接口
     *
     * @param userId
     * @return
     */
    @GetMapping("/getUser")
    String getUser(@RequestParam("userId") Long userId);
}
