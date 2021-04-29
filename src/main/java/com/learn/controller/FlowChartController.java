package com.learn.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


/**
 * @description: 流程图列表和详情接口
 * @author: Huo
 * @create: 2019-12-06 16:16
 */
@Slf4j
@RestController
@RequestMapping("/v1/organizationModal")
public class FlowChartController {

    /**
     * 组织模型树查询接口
     * @return
     */
    @GetMapping(path = "/OMTree", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject getOMTree(@RequestParam("path") String path,
                                @RequestParam("category") String category,
                                @RequestParam("depth") String depth,
                                @RequestParam("fields") String fields,
                                @RequestParam("filter") String filter) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("a","aaa");
        return jsonObject;
    }

    /**
     * 变量查询接口
     * @return
     */
    @GetMapping(path = "/var", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject getVar(@RequestParam(name = "ids") String ids){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("b","bbb");
        return jsonObject;
    }

    /**
     * 画面查询接口
     * @return
     */
    @GetMapping(path = "/pic/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject getPic(@PathVariable(name = "id") String id){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("c","ccc");
        return jsonObject;
    }

}
