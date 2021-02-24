package com.james.controller;

import com.james.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.8
 * @ClassName AjaxController
 * @Description TODO
 * @Author James
 * @date 2021/2/24 15:49
 */
@Controller
public class AjaxController {

    @RequestMapping("/ajax1")
    public void ajax1(String name, HttpServletResponse response) throws IOException {
        System.out.println(name);
        if("admin".equals(name)){
            response.getWriter().print("true");
        }else {
            response.getWriter().print("false");
        }

    }

    @RequestMapping("/ajax2")
    @ResponseBody
    public List<User> ajax2() throws IOException {

        List<User> list = new ArrayList<>();

        User user1 = new User("小郑1", 2, "男");
        User user2 = new User("小郑1", 2, "男");
        User user3 = new User("小郑1", 2, "男");
        User user4 = new User("小郑1", 2, "男");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);

        return list;

    }
}
