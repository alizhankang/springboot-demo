package com.lizhankang.springbootdemo.controller;
import com.lizhankang.springbootdemo.config.MicroServiceUrl;
import com.lizhankang.springbootdemo.dto.request.User;
import com.lizhankang.springbootdemo.sevice.SpringBootDemoService;
import com.lizhankang.springbootdemo.dto.response.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping(value = "/springboot", produces = "application/json; charset=UTF-8")
public class SpringBootDemoController {
    private final Logger LOGGER = LoggerFactory.getLogger(SpringBootDemoController.class);

    @Autowired
    private SpringBootDemoService springBootDemoService;

    // @Value 注解上通过 ${key} 即可获取配置文件中和 key 对应的 value 值。
    @Value("${url.orderUrl}")
    private String orderUrl;

    @Resource  // @Resource注解 注入配置类对象，声明该bean对象是一个配置类对象
    private MicroServiceUrl microServiceUrl;

    /*
    无参请求
     */
    // http://localhost:8080/springboot/start
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String startSpringBoot() {
        return "Welcome to the world of Spring Boot!";
    }

    // http://localhost:8080/springboot/user
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public JsonResult<User> getUser(@RequestParam(name = "idd", defaultValue = "1") String id) {
        LOGGER.info("--------- DemoController.getById start ---------");
        LOGGER.info("[DemoController.getById]根据ID查询用户信息: id = {}", id);
        // call service
        String result = springBootDemoService.getById(id);
        // 返回最终结果
        LOGGER.info("--------- DemoController.getById over ---------");
        User user = new User(1, "测试", "123456");
        return new JsonResult<>(user);
    }

    // http://localhost:8080/springboot/list
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public JsonResult<List> getUserList() {
        List<User> userList = new ArrayList<>();
        User user1 = new User(1, "测试", "123456");
        User user2 = new User(2, "测试课", "123456");
        userList.add(user1);
        userList.add(user2);
        return new JsonResult<>(userList, "获取用户列表成功");
    }

    // http://localhost:8080/springboot/map
    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public JsonResult<Map> getMap() {
        Map<String, Object> map = new HashMap<>(3);
        User user = new User(1, "测试", null);
        map.put("作者信息", user);
        map.put("博客地址", "https://blog.itcodai.com");
        map.put("CSDN地址", null);
        map.put("粉丝数量", 4153);
        return new JsonResult<>(map);
    }

    // http://localhost:8080/springboot/log
    @RequestMapping(value = "/log", method = RequestMethod.GET)
    public JsonResult<String> testLog() {
        LOGGER.debug("=====测试日志debug级别打印====");
        LOGGER.info("======测试日志info级别打印=====");
        LOGGER.error("=====测试日志error级别打印====");
        LOGGER.warn("======测试日志warn级别打印=====");
        // 可以使用占位符打印出一些参数信息
        String str1 = "blog.itcodai.com";
        String str2 = "blog.csdn.net/eson_15";
        LOGGER.info("======测试: {} {}", str1, str2);
        return new JsonResult<>("success");
    }

    // http://localhost:8080/springboot/config
    @RequestMapping("/config")
    public JsonResult<String> testConfig() {
        LOGGER.info("=====获取的订单服务地址为：{}", microServiceUrl.getOrderUrl());
        return  new JsonResult<>("成功获取到了配置文件中的 订单微服务地址: " + microServiceUrl.getOrderUrl());
    }

    @RequestMapping("/multyConfig")
    public JsonResult<String> testMultyConfig() {
        LOGGER.info("=====获取的订单服务地址为：{}", microServiceUrl.getOrderUrl());
        LOGGER.info("=====获取的用户服务地址为：{}", microServiceUrl.getUserUrl());
        LOGGER.info("=====获取的购物车服务地址为：{}", microServiceUrl.getShoppingUrl());
        return new JsonResult<>("通过配置类统一管理多个配置 并 注入 ..");
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String testGet() {
        return "success";
    }

    @GetMapping(value = "/get1")
    public String testGet1() {
        return "success";
    }

    /*
    带参请求
     */
    //  @PathValiable：获取path参数， 是从 url 模板中获取参数值， 即这种风格的 url：http://localhost:8080/user/{id}
    @GetMapping("/user/{id}")
    public String testPathVariable(@PathVariable Integer id) {
        System.out.println("获取到的id为：" + id);
        return "success";
    }

    @GetMapping("/user/{idd}/{name}")
    public String testPathVariable(@PathVariable(value = "idd") Integer id, @PathVariable String name) {
        System.out.println("获取到的id为：" + id);
        System.out.println("获取到的name为：" + name);
        return "success";
    }

    //  @RequestParam 是从 request 里面获取参数值，即这种风格的 url：http://localhost:8080/user?id=1
    @GetMapping("/user2")
    public String testRequestParam(@RequestParam Integer id) {
        System.out.println("获取到的id为：" + id);
        return "success";
    }
    // 除了 value 属性外，还有个两个属性比较常用：
    //      value 属性: url 上面的参数和方法的参数需要一致，如果不一致，也需要使用 value 属性来说明,就会去value说明的字段值
    //      required 属性：true 表示该参数必须要传，否则就会报 404 错误，false 表示可有可无。
    //      defaultValue 属性：默认值，表示如果请求中没有同名参数时的默认值。
    @RequestMapping("/user3")
    public String testRequestParam2(@RequestParam(value = "iddd", required = false) Integer id) {
        System.out.println("获取到的id为：" + id);
        return "success";
    }

    // @RequestParam 注解用于 GET 请求上时，接收拼接在 url 中的参数。
    // 除此之外，该注解还可以用于 POST 请求，接收前端表单提交的参数，
    // 假如前端通过表单提交 username 和 password 两个参数，那我们可以使用 @RequestParam 来接收，用法和上面一样。
    @PostMapping("/form1")
    public String testForm(@RequestParam String username, @RequestParam String password) {
        System.out.println("获取到的username为：" + username);
        System.out.println("获取到的password为：" + password);
        return "success";
    }

    // 如果表单数据很多，我们不可能在后台方法中写上很多参数，每个参数还要 @RequestParam 注解。
    // 针对这种情况，我们需要封装一个实体类来接收这些参数，实体中的属性名和表单中的参数名一致即可。
    // 使用实体接收的话，我们不能在前面加 @RequestParam 注解了，直接使用即可。
    @PostMapping("/form2")
    public String testForm(User user) {
        System.out.println("获取到的username为：" + user.getUsername());
        System.out.println("获取到的password为：" + user.getPassword());
        return "success";
    }

    // @RequestBody 注解用于 POST 请求上，接收 json 实体参数
    //      它和上面我们介绍的表单提交有点类似，只不过参数的格式不同，一个是 json 实体，一个是表单提交。在实际项目中根据具体场景和需要使用对应的注解即可。
    @PostMapping("/user")
    public String testRequestBody(@RequestBody User user) {
        System.out.println("获取到的username为：" + user.getUsername());
        System.out.println("获取到的password为：" + user.getPassword());
        return "success";
    }
    /*
    常用注解总结:
        1.@RestControll: @Controller + @ResponseBody 也就是说该Controller返回的所有内容都是Json格式的字符串
        2.@RequestParam: 获取URL的query参数 或 以实体类的形式获取表单参数
        3.@PathValue: 获取URL的path参数
        4.@RequestBody: 以实体类的形式获取请求体中的json参数
     */





}
