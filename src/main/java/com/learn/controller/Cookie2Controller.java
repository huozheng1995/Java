package com.learn.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
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
@RequestMapping("/cookie2")
public class Cookie2Controller {

    @GetMapping(path = "/getCookie2", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject getCookie2(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
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
