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
@RequestMapping("/session")
public class SessionController {

    //session.setAttribute默认生成一个cookie是JSESSIONID，该cookie的path是/，可以跨controller通信
    @GetMapping(path = "/setSession", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject setSession(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("mySession", "mySession233333");
        //返回数据
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", "session1");
        System.out.println("------------------------------");
        return jsonObject;
    }

}
