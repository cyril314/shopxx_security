package com.fit.web.shop;

import com.fit.base.R;
import com.fit.entity.Member;
import com.fit.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @AUTO
 * @Author AIM
 * @DATE 2025/3/6
 */
@Controller
@RequestMapping("/shop/member")
public class ShopMemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping({"checkUsername", "/checkUsername.action"})
    @ResponseBody
    public R checkUsername(Member member) {
        Member user = memberService.get(member);
        if (user != null) {
            return R.success();
        } else {
            return R.error();
        }
    }
}