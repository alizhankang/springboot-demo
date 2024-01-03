package com.lizhankang.springbootdemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
@Data
@Primary
@Component  // @Component 注解， 将bean对象作为组件放到Spring容器中，让 Spring 去管理，我们使用的时候直接注入即可。
@ConfigurationProperties(prefix = "url")  // 会根据配置文件中的信息自动创建 配置类对象
public class MicroServiceUrl {
    private String orderUrl;
    private String userUrl;
    private String shoppingUrl;
    // 省去get和set方法

}