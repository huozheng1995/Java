package com.learn.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 状态检查
 * @author: Huo
 * @create: 2019-12-17 14:18
 */
@Slf4j
@RestController
@RequestMapping("/cookie")
public class CookieController {

    //new Cookie添加的cookie默认的path是当前contorller，也就是/cookie，不能跨controller通信
    @GetMapping(path = "/setCookie", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject setCookie(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        //读取浏览器cookie
        Cookie[] cookies = httpServletRequest.getCookies();
        if(cookies != null) {
            for(Cookie cookie: cookies) {
                System.out.println(cookie.getName()+ ": " + cookie.getValue());
            }
        }

        //关闭浏览器过期，关闭页签不过期
        Cookie cookie1 = new Cookie("cookie1", System.currentTimeMillis()+"");
        cookie1.setMaxAge(-1);
        httpServletResponse.addCookie(cookie1);

        //立即过期
        Cookie cookie2 = new Cookie("cookie2", System.currentTimeMillis()+"");
        cookie2.setMaxAge(0);
        httpServletResponse.addCookie(cookie2);

        //过期时间很长,写在浏览器端磁盘中
        Cookie cookie3 = new Cookie("cookie3", System.currentTimeMillis()+"");
        cookie3.setMaxAge(Integer.MAX_VALUE);
        httpServletResponse.addCookie(cookie3);

        //cookie4设置path为/，这样才可以跨controller通信
        Cookie cookie4 = new Cookie("cookie4", System.currentTimeMillis()+"");
        cookie4.setMaxAge(Integer.MAX_VALUE);
        cookie4.setPath("/");
        httpServletResponse.addCookie(cookie4);

        //返回数据
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", "cookie");
        return jsonObject;
    }

    @GetMapping(path = "/getCookie", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject getCookie(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        //读取浏览器cookie
        Cookie[] cookies = httpServletRequest.getCookies();
        if(cookies != null) {
            for(Cookie cookie: cookies) {
                System.out.println(cookie.getName()+ ": " + cookie.getValue());
            }
        }
        //返回数据
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", "reqTestCors");
        System.out.println("------------------------------");
        return jsonObject;
    }
}
