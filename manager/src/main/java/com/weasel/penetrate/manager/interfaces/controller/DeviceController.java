package com.weasel.penetrate.manager.interfaces.controller;

import com.google.gson.Gson;
import com.weasel.penetrate.manager.domain.User;
import com.weasel.penetrate.manager.domain.device.Device;
import com.weasel.penetrate.manager.infrastructure.exception.DevicePortBindedException;
import com.weasel.penetrate.manager.infrastructure.exception.DevicePortUsedUpException;
import com.weasel.penetrate.manager.infrastructure.exception.DeviceSubDomainUsedException;
import com.weasel.penetrate.manager.infrastructure.helper.SecurityHelper;
import com.weasel.penetrate.manager.infrastructure.task.ReloadFrpConfigQueue;
import com.weasel.penetrate.manager.infrastructure.task.ReloadFrpConfigScheduled;
import com.weasel.penetrate.manager.interfaces.vo.ResponseMessage;
import com.weasel.penetrate.manager.service.DeviceService;
import com.weasel.penetrate.manager.service.UserService;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Dylan
 * @date 2017/1/22.
 */
@Controller
@RequestMapping(value = "/device")
public class DeviceController{

    private final static Logger logger = LoggerFactory.getLogger(DeviceController.class);
    private final static String customDomainSuffix = "tunnel.kisme.org";

    private int portStart = 8000;
    private int portEnd = 9999;

    @Autowired
    private DeviceService service;

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/list_view"},method = GET)
    public String listView(){

        return "device";
    }

    @ResponseBody
    @RequestMapping(value = {"/list"},method = GET)
    public String list(){

        Device device = new Device();
        if(!SecurityHelper.isAdmin()){
            User currentUser = SecurityHelper.getCurrentUser();
            device.setUsername(currentUser.getName());
        }
        List<Device> devices = service.query(device);

        return new Gson().toJson(devices);
    }

    @ResponseBody
    @RequestMapping(value = {"/save"})
    public ResponseMessage save(Device device){

        User user = SecurityHelper.getCurrentUser();
        if(user.getDevice() < user.getTotalDevice()){
            try {
                int port = service.distributePort();
                device.setListenPort(String.valueOf(port));
                String number = String.valueOf(RandomUtils.nextInt());
                device.setUsername(user.getName());
                device.setNumber(number);
                if(StringUtils.isBlank(device.getCustomDomains())
                        &&(StringUtils.equalsIgnoreCase("http",device.getProtocolType().getValue())
                        || StringUtils.equalsIgnoreCase("https",device.getProtocolType().getValue()))){

                    device.setCustomDomains(device.getNumber()+"."+customDomainSuffix);
                }

                service.save(device);
                user.setDevice(user.getDevice()+1);
                userService.save(user);
                ReloadFrpConfigScheduled.submitTask(new ReloadFrpConfigQueue.ReloadFrpConfigTask());
                return ResponseMessage.success();
            } catch (DevicePortBindedException e) {
                logger.error(e.getMessage());
            } catch (DeviceSubDomainUsedException e) {
                logger.error(e.getMessage());
            } catch (DevicePortUsedUpException e) {
                logger.error(e.getMessage());
            }
            return ResponseMessage.error();
        }
        return ResponseMessage.error();
    }

}
