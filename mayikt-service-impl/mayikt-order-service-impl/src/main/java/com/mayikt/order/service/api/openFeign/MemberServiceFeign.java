package com.mayikt.order.service.api.openFeign;

import com.mayikt.member.service.api.MemberService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "mayikt-member")
public interface MemberServiceFeign extends MemberService {
}
