/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lizhankang.springbootdemo.controller;

import com.lizhankang.springbootdemo.dto.request.ReqUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Slf4j
@Controller
@RequestMapping(value = "/basiccontroller", produces = "application/json; charset=UTF-8")
public class BasicController {

    // http://127.0.0.1:8080/hello?name=lisi
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam(name = "name2", defaultValue = "unknown user", required = true) String name) {
        log.info("日志记录: {}", name);
        return "Hello " + name;
    }

    // http://127.0.0.1:8080/user
    @RequestMapping("/user")
    @ResponseBody
    public ReqUser user() {
        ReqUser reqUser = new ReqUser();
        reqUser.setUser_name("lisi");
        reqUser.setPass_word("666");
        log.info("日志记录: {}", reqUser);
        return reqUser;
    }

    // http://127.0.0.1:8080/save_user
    @RequestMapping("/save_user")
    @ResponseBody
    public String saveUser(ReqUser u) {
        return "user will save: name=" + u.getUser_name() + ", password=" + u.getPass_word();
    }

    // http://127.0.0.1:8080/html
    @RequestMapping("/html")
    public String html() {
        return "index.html";
    }

    /*
    这是干啥的？
     */
    @ModelAttribute
    public void parseUser(@RequestParam(name = "name", defaultValue = "unknown user") String name
            , @RequestParam(name = "age", defaultValue = "12") Integer age, ReqUser reqUser) {
        reqUser.setUser_name("zhangsan");
        reqUser.setPass_word("18");
    }
}
