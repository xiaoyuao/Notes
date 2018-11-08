package com.smart.controller;

import com.smart.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by admin on 2018/11/8.
 */
@RestController
@RequestMapping("fileController")
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/upload")
    public String upload(HttpServletRequest request, String info){
        fileService.upload(request,info);
        return "success";
    }
}
