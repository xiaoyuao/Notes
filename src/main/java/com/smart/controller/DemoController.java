package com.smart.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2018/8/8.
 */
@RestController
public class DemoController {
    @Value("${user.name}")
    private String username;

    @Value("${user.pswd}")
    private String password;

    @Value("${JAVA_HOME}")
    private String javahome;

    @Value("${user.database}")
    private String database;

    /**
     *@desc
     * @RequestMapping
     * consumes，produces；
        consumes： 指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;
        produces: 指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；

     @PathVariable、@RequestParam、@RequestAttribute

     @PathVariable用来接收参数,如/path/001,可接收001作为参数
     @RequestParam 用来接收URL中的参数,如/param?id=001,可接收001作为参数
     @RequestAttribute用于访问由过滤器或拦截器创建的、预先存在的请求属性，效果等同与request.getAttrbute().
     */

    @RequestMapping("/info")
    String index(){
        return "userName:"+username+" password:"+password+":"+javahome+" database:"+database;
    }


    @RequestMapping("/te")
    String te(){
        return "te";
    }

    @GetMapping("/path/{param}")
    public String PathVariable(@PathVariable("param")String param){
        return "Param is "+param;
    }

    @GetMapping("/param")
    public String RequestParam(@RequestParam("param")String param){
        return "Param is "+param;
    }

    @RequestMapping("/normal")
    public Map normal(@RequestBody String param){
       // JSONObject json = JSON.parse
        Map m = new HashMap();
        m.put("param","Param is "+param);
     //   m.put("version","version is "+version);
        return m;
    }

    @RequestMapping(value = "/jquery")
    public Map jquery(String param){
        Map m = new HashMap();
        m.put("param","Param is "+param);
        return m;
    }
}
