package com.weasel.penetrate.manager.infrastructure.ini;

import com.weasel.penetrate.manager.domain.Common;
import com.weasel.penetrate.manager.domain.IniConfig;
import com.weasel.penetrate.manager.domain.device.Device;
import org.apache.commons.lang3.StringUtils;

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
        write("common","vhost_http_port",common.getVhostHttpPort());
        write("common","vhost_https_port",common.getVhostHttpsPort());
        write("common","dashboard_port",common.getDashboardPort());
        write("common","dashboard_user",common.getDashboardUser());
        write("common","dashboard_pwd",common.getDashboardPwd());
        write("common","privilege_mode",common.isPrivilegeMode());
        write("common","privilege_token",common.getPrivilegeToken());
        write("common","privilege_allow_ports",common.getPrivilegeAllowPorts());
        write("common","max_pool_count",common.getMaxPoolCount());
        write("common","authentication_timeout",common.getAuthenticationTimeout());
        write("common","subdomain_host",common.getSubdomainHost());
        Set<Device> devices = config.getDevices();
        devices.forEach(device -> {
            write(device.getNumber(),"type",device.getProtocolType().getValue());
            write(device.getNumber(),"auth_token",device.getAuthToken());
            write(device.getNumber(),"bind_addr",device.getBindAddr());
            write(device.getNumber(),"listen_port",device.getListenPort());
            write(device.getNumber(),"custom_domains",device.getCustomDomains());
            write(device.getNumber(),"locations",device.getLocations());
        });

        return this;
    }
    public IniWriter write(String section,String key,Object value){
        if(null != value){
            if(value instanceof String && StringUtils.isEmpty(String.valueOf(value))){
                return this;
            }
            ini.put(section,key,value);
        }
        return this;
    }

    public void store(File file) throws IOException {
        ini.store(file);
    }

}
