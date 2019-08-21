package com.smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 2019/8/21.
 */
@RestController
public class RedisController {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;



    @RequestMapping("/insertRedis")
    public void insertRedis(){

        String key= "key"+(int)Math.random()*100;

        redisTemplate.opsForValue().set("key00","yes");

        redisTemplate.opsForValue().set(key,"hahahah");

        stringRedisTemplate.opsForValue().set("keystring","yesstring");

        System.out.println("1111");

    }

    @RequestMapping("/getRedis")
    public void getRedis(){
       String a =  redisTemplate.opsForValue().get("key00").toString();
        System.out.println(a);
       String b = stringRedisTemplate.opsForValue().get("keystring").toString();
        System.out.println(b);
    }

}
