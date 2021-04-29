package com.learn.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 跨域
 * @author: Huo
 * @create: 2019-12-17 14:18
 */
@Slf4j
@RestController
@RequestMapping("/cors")
public class CorsController {

    @GetMapping(path = "/getCors", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject getCors(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        //返回数据
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", "reqTestCors");
        System.out.println("------------------------------");
        return jsonObject;
    }
}
