package com.fit.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @AUTO
 * @Author AIM
 * @DATE 2025/3/7
 */
@Controller
@RequestMapping("/menu")
public class AdminMenuController {

    @RequestMapping("/admin")
    public String admin(HttpServletRequest request, HttpServletResponse response) {
        return "/admin/menu_admin";
    }

    @RequestMapping("/common")
    public String common(HttpServletRequest request, HttpServletResponse response) {
        return "/admin/menu_common";
    }

    @RequestMapping("/content")
    public String content(HttpServletRequest request, HttpServletResponse response) {
        return "/admin/menu_content";
    }

    @RequestMapping("/member")
    public String member(HttpServletRequest request, HttpServletResponse response) {
        return "/admin/menu_member";
    }

    @RequestMapping("/order")
    public String order(HttpServletRequest request, HttpServletResponse response) {
        return "/admin/menu_order";
    }

    @RequestMapping("/product")
    public String product(HttpServletRequest request, HttpServletResponse response) {
        return "/admin/menu_product";
    }

    @RequestMapping("/setting")
    public String setting(HttpServletRequest request, HttpServletResponse response) {
        return "/admin/menu_setting";
    }
}