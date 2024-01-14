package com.lizhankang.springbootdemo.sevice;

import com.lizhankang.springbootdemo.config.Api;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SpringBootDemoService {
    private final Logger LOG = LoggerFactory.getLogger(SpringBootDemoService.class);

    // 注入dao层

    public String getById(String id) {
        LOG.info("--------- DemoService.getById start ---------");
        // 查询数据库

        // 调用第三方api
        Api.VipGateWay.get(id);
        LOG.info("--------- DemoService.getById over ---------");
        return "数据库返回: ******* (MOCK)";

    }
}
