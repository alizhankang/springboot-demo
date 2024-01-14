package com.lizhankang.springbootdemo.controller;

import com.lizhankang.springbootdemo.dto.response.JsonResult;
import com.lizhankang.springbootdemo.exception.BizException;
import com.lizhankang.springbootdemo.exception.BizExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exception")
public class ExceptionController {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @PostMapping("/test")
    public JsonResult test(@RequestParam("name") String name,
                           @RequestParam("pass") String pass) {
        logger.info("name：{}", name);
        logger.info("pass：{}", pass);
        return new JsonResult();
    }

    // http://localhost:8080/exception/business
    @GetMapping("/business")
    public JsonResult testException() {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            throw new BizException(BizExceptionEnum.UNEXPECTED_EXCEPTION);
        }
        return new JsonResult();
    }

}
