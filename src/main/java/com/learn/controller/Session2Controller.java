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
import javax.servlet.http.HttpSession;

/**
 * @description: 状态检查
 * @author: Huo
 * @create: 2019-12-17 14:18
 */
@Slf4j
@RestController
@RequestMapping("/session2")
public class Session2Controller {

    @GetMapping(path = "/getSession2", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject getSession2(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        HttpSession session = httpServletRequest.getSession();
        String mySession = (String)session.getAttribute("mySession");
        System.out.println(mySession);
        //返回数据
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", "session2");
        System.out.println("------------------------------");
        return jsonObject;
    }

}
