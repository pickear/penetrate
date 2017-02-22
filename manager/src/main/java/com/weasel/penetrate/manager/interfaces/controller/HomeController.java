package com.weasel.penetrate.manager.interfaces.controller;

import com.weasel.penetrate.manager.infrastructure.Frp;
import com.weasel.penetrate.manager.service.FrpConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Dylan
 * @date 2017/1/22.
 */
@Controller
public class HomeController {

    private final static Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = {"/","home","index"},method = GET)
    public String home(){

        return "home";
    }

    @RequestMapping(value = "/welcome",method = GET)
    public String welcome(){

        return "welcome";
    }

}
