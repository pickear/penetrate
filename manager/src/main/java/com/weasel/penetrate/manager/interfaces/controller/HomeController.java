package com.weasel.penetrate.manager.interfaces.controller;

import com.weasel.penetrate.manager.domain.User;
import com.weasel.penetrate.manager.infrastructure.helper.SecurityHelper;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Dylan
 * @date 2017/1/22.
 */
@Controller
public class HomeController {

    private final static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = {"/","home","index"},method = GET)
    public String home(Model model){

        model.addAttribute("currentUser", SecurityHelper.getCurrentUser());
        return "home";
    }

    @RequestMapping(value = "/welcome",method = GET)
    public String welcome(){

        return "welcome";
    }

    @RequestMapping(value = "/login",method = GET)
    public String login(User user, HttpServletRequest request){

        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            currentUser.logout();
        }

        boolean rememberMe = ServletRequestUtils.getBooleanParameter(request, "rememberMe", false);
        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword(), rememberMe);
        currentUser.login(token); // 登录

        return "redirect:/home";
    }

    @RequestMapping(value = "/logout",method = GET)
    public String logout(RedirectAttributes redirectAttributes) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
        }
        redirectAttributes.addFlashAttribute("message", "您已安全退出");
        return "redirect:/login";
    }

}
