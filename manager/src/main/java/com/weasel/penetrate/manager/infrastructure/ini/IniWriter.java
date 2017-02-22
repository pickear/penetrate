package com.weasel.penetrate.manager.infrastructure.ini;

import com.weasel.penetrate.manager.domain.Common;
import com.weasel.penetrate.manager.domain.IniConfig;
import com.weasel.penetrate.manager.domain.device.Device;

import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * @author Dylan
 * @date 2017/1/18.
 */
public class IniWriter extends IniHandler{

    private IniWriter(){}

    public static IniWriter create(){
        return new IniWriter();
    }

    public IniWriter load(IniConfig config){

        Common common = config.getCommon();
        write("common","bind_addr",common.getBindAddr());
        write("common","bind_port",common.getBindPort());
        write("common","log_file",common.getLogFile());
        write("common","log_level",common.getLogLevel());
        write("common","log_max_days",common.getLogMaxDays());

        Set<Device> devices = config.getDevices();
        devices.forEach(device -> {
            write(device.getDeviceType().getValue()+"_"+device.getNumber(),"type",device.getProtocolType().getValue());
            write(device.getDeviceType().getValue()+"_"+device.getNumber(),"auth_token",device.getAuthToken());
            write(device.getDeviceType().getValue()+"_"+device.getNumber(),"bind_addr",device.getBindAddr());
            write(device.getDeviceType().getValue()+"_"+device.getNumber(),"listen_port",device.getListenPort());
        });

        return this;
    }
    public IniWriter write(String section,String key,Object value){
        ini.put(section,key,value);
        return this;
    }

    public void store(File file) throws IOException {
        ini.store(file);
    }

}
