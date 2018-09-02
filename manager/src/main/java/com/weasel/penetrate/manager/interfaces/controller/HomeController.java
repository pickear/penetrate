package com.weasel.penetrate.manager.interfaces.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Dylan
 * @date 2017/1/20.
 */
@Controller
public class HomeController {

    @RequestMapping(value = {"/","/home","/index"},method = GET)
    public String home(Model model){

        return "home";
    }

    @RequestMapping(value = {"/course"},method = GET)
    public String course(Model model){

        return "course";
    }

    @RequestMapping(value = {"/frp-status"},method = GET)
    public String frpStatus(){

        return "frp_status";
    }
}
