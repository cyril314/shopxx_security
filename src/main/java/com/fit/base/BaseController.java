package com.fit.base;

import com.alibaba.fastjson.JSONObject;
import com.fit.entity.FriendLink;
import com.fit.entity.Navigation;
import com.fit.service.FriendLinkService;
import com.fit.service.NavigationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @AUTO 控制层基类
 * @FILE BaseController.java
 * @DATE 2018-3-23 下午2:38:18
 * @Author AIM
 */
@Slf4j
public class BaseController {

    public static final String SUCCESS = "admin/success";
    public static final String ERROR = "admin/error";
    public static final String STATUS = "status";

    @Autowired
    private NavigationService navigationService;
    @Autowired
    private FriendLinkService friendLinkService;


    public String ajaxJson(HttpServletResponse response, Object data) {
        return ajax(response, toJSONString(data), "text/html");
    }

    protected String ajax(HttpServletResponse response, String content, String type) {
        try {
            response.setContentType(type + ";charset=UTF-8");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.getWriter().write(content);
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String toJSONString(Object obj) {
        return JSONObject.toJSONString(obj);
    }

    /**
     * 将数据保存到session
     *
     * @param key   存入session键名
     * @param value 存入session值
     */
    protected void setValue2Session(HttpServletRequest res, String key, Object value) {
        res.getSession().setAttribute(key, value);
    }

    /**
     * 从session中获取数据
     *
     * @param key 取出session键名
     */
    protected Object getValue2Session(HttpServletRequest res, String key) {
        return res.getSession().getAttribute(key);
    }

    /**
     * 将数据从session中删除
     *
     * @param key 删除session键名
     */
    protected void delValue2Session(HttpServletRequest res, String key) {
        res.getSession().removeAttribute(key);
    }

    /**
     * 获取请求的所有参数,返回值Map
     */
    protected Map<String, Object> getRequestParamsMap(HttpServletRequest request) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        try {
            // 参数Map
            Map<String, String[]> properties = request.getParameterMap();
            String value = "";
            for (String strKey : properties.keySet()) {
                Object strObj = properties.get(strKey);
                if (null == strObj) {
                    value = "";
                } else if (strObj instanceof String[]) {
                    String[] values = (String[]) strObj;
                    for (int i = 0; i < values.length; i++) { // 用于请求参数中有多个相同名称
                        value = values[i] + ",";
                    }
                    value = value.substring(0, value.length() - 1);
                } else {
                    value = strObj.toString();// 用于请求参数中请求参数名唯一
                }
                returnMap.put(strKey.toString(), value);
            }
        } catch (Exception e) {
            log.error("getRequestParamsMap错误信息：{}", e);
        }
        return returnMap;
    }

    protected boolean isMobileDevice(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        return userAgent != null && (userAgent.contains("Mobi") || userAgent.contains("Android") || userAgent.contains("iPhone"));
    }

    public static boolean containsIgnoreCase(String source, String target) {
        if (source == null || target == null) {
            return false;
        }
        return source.toLowerCase().contains(target.toLowerCase());
    }

    public static String substringAfterLast(String str, String separator) {
        if (StringUtils.hasLength(str)) {
            return str;
        }
        if (StringUtils.hasLength(separator)) {
            return "";
        }
        int pos = str.lastIndexOf(separator);
        if ((pos == -1) || (pos == str.length() - separator.length())) {
            return "";
        }
        return str.substring(pos + separator.length());
    }

    public static String md5Hex(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }

    public String toFront(HttpServletRequest request, String url) {
        Map<String, Object> param = new HashMap<>();
        param.put("position", 0);
        param.put("isVisible", 1);
        List<Navigation> tops = this.navigationService.findList(param);
        request.setAttribute("topNavigationList", tops);
        param.put("position", 1);
        List<Navigation> middles = this.navigationService.findList(param);
        request.setAttribute("middleNavigationList", middles);
        param.put("position", 2);
        List<Navigation> bottoms = this.navigationService.findList(param);
        request.setAttribute("bottomNavigationList", bottoms);
        param.clear();
        List<FriendLink> tlist = new ArrayList();
        List<FriendLink> plist = this.friendLinkService.findList(param);
        for (FriendLink friendLink : plist) {
            if (ObjectUtils.isEmpty(friendLink.getLogo())) {
                tlist.add(friendLink);
            }
        }
        plist.removeAll(tlist);
        request.setAttribute("pictureFriendLinkList", plist);
        request.setAttribute("textFriendLinkList", tlist);
        return String.format("/shop/%s", url);
    }
}
