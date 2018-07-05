package com.weasel.penetrate.manager.domain.device;

import com.weasel.penetrate.manager.infrastructure.annotation.JQGridId;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author Dylan
 * @date 2017/1/22.
 */
public class Device  implements Serializable {

    @JQGridId
    private long id;

    /**
     * 所属用户
     */
    private String username;

    /**
     * 设备编码
     */
    private String number;

    /**
     * 协议类型
     */
    protected ProtocolType protocolType;
    /**
     * 密码
     */
    protected String authToken;
    /**
     * 绑定的地址
     */
    protected String bindAddr = "0.0.0.0";
    /**
     * 监听的端口
     */
    protected String listenPort;
    /**
     * 域名
     */
    private String customDomains;
    /**
     * path,
     */
    private String locations;

    public Device() {
    }

    public Device(ProtocolType protocolType) {
        this.protocolType = protocolType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public ProtocolType getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(ProtocolType protocolType) {
        this.protocolType = protocolType;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getBindAddr() {
        return bindAddr;
    }

    public void setBindAddr(String bindAddr) {
        this.bindAddr = bindAddr;
    }

    public String getListenPort() {
        return listenPort;
    }

    public void setListenPort(String listenPort) {
        this.listenPort = listenPort;
    }

    public String getCustomDomains() {
        return customDomains;
    }

    public void setCustomDomains(String customDomains) {
        this.customDomains = customDomains;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public boolean isHttpProtocol(){
        return StringUtils.equalsIgnoreCase("http",getProtocolType().getValue());
    }
    public boolean isHttpsProtocol(){
        return StringUtils.equalsIgnoreCase("https",getProtocolType().getValue());
    }

    public String name(){
        return StringUtils.lowerCase(this.getClass().getSimpleName());
    }

    public static Device tcp(){
        return new Device( ProtocolType.TCP);
    }

    public static Device udp(){
        return new Device(ProtocolType.UDP);
    }

    public static Device http(){
        return new Device(ProtocolType.HTTP);
    }
    public static Device https(){
        return new Device(ProtocolType.HTTPS);
    }

    public static enum ProtocolType {
        TCP("tcp"),UDP("udp"),HTTP("http"),HTTPS("https");
        private String value;

        ProtocolType() {
        }

        ProtocolType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
