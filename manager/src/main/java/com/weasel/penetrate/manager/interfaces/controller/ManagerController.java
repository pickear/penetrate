package com.weasel.penetrate.manager.interfaces.controller;

import com.weasel.penetrate.manager.domain.User;
import com.weasel.penetrate.manager.infrastructure.exception.UserExistException;
import com.weasel.penetrate.manager.infrastructure.helper.SecurityHelper;
import com.weasel.penetrate.manager.service.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author Dylan
 * @date 2017/1/22.
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {
    private final static Logger logger = LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = {""},method = GET)
    public String home(Model model){

        model.addAttribute("currentUser", SecurityHelper.getCurrentUser());
        return "manager/home";
    }

    @RequestMapping(value = "/welcome",method = GET)
    public String welcome(){

        return "manager/welcome";
    }

    @RequestMapping(value = "/login",method = GET)
    public String login(){

        return "manager/login";
    }

    @RequestMapping(value = "/login",method = POST)
    public String login(User user, HttpServletRequest request){

        try {

            Subject currentUser = SecurityUtils.getSubject();
            if (currentUser.isAuthenticated()) {
                currentUser.logout();
            }

            boolean rememberMe = ServletRequestUtils.getBooleanParameter(request, "rememberMe", false);
            UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword(), rememberMe);
            currentUser.login(token); // 登录
            user = SecurityHelper.getCurrentUser();
            user.setLoginCount(user.getLoginCount()+1);
            user.setLoginTime(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
            userService.save(user);
        }catch (IncorrectCredentialsException exception){
            logger.error(exception.getMessage());
            return "redirect:/manager/login?code=1";
        }

        return "redirect:/manager";
    }

    @RequestMapping(value = "/logout",method = GET)
    public String logout(RedirectAttributes redirectAttributes) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
        }
        redirectAttributes.addFlashAttribute("message", "您已安全退出");
        return "redirect:/manager/login";
    }

    @RequestMapping(value = "/register",method = GET)
    public String register(){

        return "manager/register";
    }

    @RequestMapping(value = "/register",method = POST)
    public String register(User user){

        try {
            if(userService.notExist(user)){
                user = user.createSalt().encodePassword();
                user.setCreateTime(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
                userService.save(user);
                return "redirect:/manager/login";
            }
            return "redirect:/manager/register?code=0";
        } catch (UserExistException e) {
            e.printStackTrace();
            return "redirect:/manager/register?code=1";
        }
    }

}
