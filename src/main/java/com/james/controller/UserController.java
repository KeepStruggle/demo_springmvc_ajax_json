package com.james.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.james.pojo.User;
import com.james.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @version 1.8
 * @ClassName UserController
 * @Description TODO
 * @Author James
 * @date 2021/2/24 10:42
 */
@Controller
public class UserController {

    @RequestMapping("/json1")
    //思考问题，我们正常返回它会走驶入解析器，而json需要返回的是一个字符串
    @ResponseBody
    //@ResponseBody 将服务端返回的对象转换为json对象响应回去，不进视图解析器；（但是在这里我们还是先手动地将对象解析成json格式的字符串，再响应回去，其实不写也可以自动解析）
    public String json1() throws JsonProcessingException {
        //需要一个jackson的对象映射器，就是一个类，使用它可以直接将对象转换为json字符串
        ObjectMapper mapper = new ObjectMapper();

        //创建一个对象
        User user = new User("小郑", 2, "男");
        System.out.println(user);

        //将java对象转换为json字符串
        String str = mapper.writeValueAsString(user);
        System.out.println(str);

        return str;
    }

    //发现一个问题，乱码了，怎么解决？ 给RequestMapping加一个属性
    //发现出现了乱码问题,我么需要设置一个它的编码格式为utf-8, 以及它返回的类型；
    // 通过@RequestMapping的produces属性来实现，修改代码如下
    @RequestMapping(value = "/json2", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String json2() throws JsonProcessingException {

        User user = new User("小郑", 2, "男");

        return new ObjectMapper().writeValueAsString(user);
    }

    @RequestMapping(value = "/json3")
    @ResponseBody
    public String json3() throws JsonProcessingException {
        List<User> list = new ArrayList<>();
        User user1 = new User("小郑1", 2, "男");
        User user2 = new User("小郑1", 2, "男");
        User user3 = new User("小郑1", 2, "男");
        User user4 = new User("小郑1", 2, "男");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);

        return new ObjectMapper().writeValueAsString(list);
    }

    @RequestMapping(value = "/json4")
    @ResponseBody
    public String json4() throws JsonProcessingException {
        Date date = new Date();
        System.out.println(date);
        //发现问题：时间默认返回的json字符串变成了时间戳的格式：1614138342225 Timestamp

        return new ObjectMapper().writeValueAsString(date);
    }

    @RequestMapping(value = "/json5")
    @ResponseBody
    public String json5() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        //1.如何让他不返回时间戳，所以我们要关闭它的时间戳功能
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //2.时间格式化问题！自定义格式对象
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //3.让mapper指定时间日期格式为simpleDateFormat
        mapper.setDateFormat(simpleDateFormat);

        //写一个日期对象
        Date date = new Date();

        return mapper.writeValueAsString(date);
    }

    @RequestMapping(value = "/json6")
    @ResponseBody
    public String json6() throws JsonProcessingException {
        //写一个日期对象
        Date date = new Date();

        return JsonUtils.getJson(date);
    }


}
