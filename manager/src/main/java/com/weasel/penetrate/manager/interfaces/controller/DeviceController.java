package com.weasel.penetrate.manager.interfaces.controller;

import com.google.gson.Gson;
import com.weasel.penetrate.manager.domain.device.Device;
import com.weasel.penetrate.manager.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Dylan
 * @date 2017/1/22.
 */
@Controller
@RequestMapping(value = "/device")
public class DeviceController{

    @Autowired
    private DeviceService service;


    @RequestMapping(value = {"/list"},method = GET)
    public String list(){

        Device device = new Device();
        List<Device> devices = service.query(device);

        return new Gson().toJson(devices);
    }
}
