package com.weasel.penetrate.manager.interfaces.controller;

import com.weasel.penetrate.common.helper.GsonHelper;
import com.weasel.penetrate.manager.domain.Page;
import com.weasel.penetrate.manager.domain.User;
import com.weasel.penetrate.manager.infrastructure.exception.UserExistException;
import com.weasel.penetrate.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Dylan
 * @date 2017/1/22.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController{

    @Autowired
    private UserService service;


    @RequestMapping(value = "/regitster",method = GET)
    public String register(){

        return "";
    }

    @RequestMapping(value = "/regitster",method = POST)
    public String register(User user){

        try {
            if(service.notExist(user)){
                int result = service.save(user);
            }
            return "";
        } catch (UserExistException e) {
            e.printStackTrace();
            return "";
        }
    }

    @RequestMapping(value = "/list",method = GET)
    @ResponseBody
    public String list(){

        Page<User> page = service.queryPage(new Page<>());

        String gson = GsonHelper.toGson(page.getResult());
        return gson;
    }

    @RequestMapping(value = "/list_view",method = GET)
    public String listView(){

        return "user";
    }
}
