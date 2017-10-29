package com.yufeimen.application.controller;

import com.yufeimen.application.service.impl.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class FileController {

    @Autowired
    FileService fileService;

    @ResponseBody
    @RequestMapping(value = "/test")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    public boolean test(HttpServletRequest request,HttpServletResponse response){
        return true;
    }

//    @ResponseBody
//    @RequestMapping(value = "/file/postFile")
//    public LinkedList<XLSModel> upload(HttpServletRequest request, HttpServletResponse response) throws IOException, IllegalAccessException {
//        return fileService.upload((MultipartHttpServletRequest) request,response);
//    }

}
