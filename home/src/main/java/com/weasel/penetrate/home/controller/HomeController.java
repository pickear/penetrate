package com.weasel.penetrate.home.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Dylan
 * @date 2017/1/20.
 */
@Controller
public class HomeController{

    @Value("${penetrate.home.domain}")
    private String homeDomain;
    @Value("${penetrate.manager.domain}")
    private String managerDomain;

    @RequestMapping(value = {"/","/home","/index"},method = GET)
    public String home(Model model){

        model.addAttribute("homeDomain",homeDomain);
        model.addAttribute("managerDomain",managerDomain);
        return "home";
    }

    @RequestMapping(value = {"/course"},method = GET)
    public String course(Model model){

        model.addAttribute("homeDomain",homeDomain);
        model.addAttribute("managerDomain",managerDomain);

        return "course";
    }
}
