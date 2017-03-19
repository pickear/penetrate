package com.weasel.penetrate.manager.domain;

/**
 * @author Dylan
 * @date 2017/1/22.
 */
public class Common {

    /**
     * A literal address or host name for IPv6 must be enclosed
     * in square brackets, as in "[::1]:80", "[ipv6-host]:http" or "[ipv6-host%zone]:80"
     */
    private String bindAddr = "0.0.0.0";
    private String bindPort = "7000";
    /**
     * if you want to support virtual host, you must set the http port for listening (optional)
     */
    private String vhostHttpPort = "80";
    private String vhostHttpsPort = "443";
    /**
     * if you want to configure or reload frps by dashboard, dashboard_port must be set
     */
    private String dashboardPort = "7500";
    /**
     * dashboard user and pwd for basic auth protect, if not set, both default value is admin
     */
    private String dashboardUser = "admin";
    private String dashboardPwd = "admin";
    /**
     * dashboard assets directory(only for debug mode)
     * assets_dir = ./static
     * console or real logFile path like ./frps.log
     */
    private String logFile = "./frps.log";
    /**
     * debug, info, warn, error
     */
    private String logLevel = "info";
    private int logMaxDays = 3;
    /**
     * if you enable privilege mode, frpc can create a proxy without pre-configure in frps when privilege_token is correct
     */
    private boolean privilegeMode = false;
    private String privilegeToken = "123";
    /**
     * only allow frpc to bind ports you list, if you set nothing, there won't be any limit
     */
    private String privilegeAllowPorts = "2000-3000,3001,3003,4000-50000";
    /**
     * pool_count in each proxy will change to max_pool_count if they exceed the maximum value
     */
    private int maxPoolCount = 100;
    /**
     * authentication_timeout means the timeout interval (seconds) when the frpc connects frps
     * if authentication_timeout is zero, the time is not verified, default is 900s
     */
    private int authenticationTimeout = 900;
    /**
     * if subdomain_host is not empty, you can set subdomain when type is http or https in frpc's configure file
     * when subdomain is test, the host used by routing is test.frps.com
     */
    private String subdomainHost = "tunnel.kisme.org";

    public String getBindAddr() {
        return bindAddr;
    }

    public void setBindAddr(String bindAddr) {
        this.bindAddr = bindAddr;
    }

    public String getBindPort() {
        return bindPort;
    }

    public void setBindPort(String bindPort) {
        this.bindPort = bindPort;
    }

    public String getVhostHttpPort() {
        return vhostHttpPort;
    }

    public void setVhostHttpPort(String vhostHttpPort) {
        this.vhostHttpPort = vhostHttpPort;
    }

    public String getVhostHttpsPort() {
        return vhostHttpsPort;
    }

    public void setVhostHttpsPort(String vhostHttpsPort) {
        this.vhostHttpsPort = vhostHttpsPort;
    }

    public String getDashboardPort() {
        return dashboardPort;
    }

    public void setDashboardPort(String dashboardPort) {
        this.dashboardPort = dashboardPort;
    }

    public String getDashboardUser() {
        return dashboardUser;
    }

    public void setDashboardUser(String dashboardUser) {
        this.dashboardUser = dashboardUser;
    }

    public String getDashboardPwd() {
        return dashboardPwd;
    }

    public void setDashboardPwd(String dashboardPwd) {
        this.dashboardPwd = dashboardPwd;
    }

    public String getLogFile() {
        return logFile;
    }

    public void setLogFile(String logFile) {
        this.logFile = logFile;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public int getLogMaxDays() {
        return logMaxDays;
    }

    public void setLogMaxDays(int logMaxDays) {
        this.logMaxDays = logMaxDays;
    }

    public boolean isPrivilegeMode() {
        return privilegeMode;
    }

    public void setPrivilegeMode(boolean privilegeMode) {
        this.privilegeMode = privilegeMode;
    }

    public String getPrivilegeToken() {
        return privilegeToken;
    }

    public void setPrivilegeToken(String privilegeToken) {
        this.privilegeToken = privilegeToken;
    }

    public String getPrivilegeAllowPorts() {
        return privilegeAllowPorts;
    }

    public void setPrivilegeAllowPorts(String privilegeAllowPorts) {
        this.privilegeAllowPorts = privilegeAllowPorts;
    }

    public int getMaxPoolCount() {
        return maxPoolCount;
    }

    public void setMaxPoolCount(int maxPoolCount) {
        this.maxPoolCount = maxPoolCount;
    }

    public int getAuthenticationTimeout() {
        return authenticationTimeout;
    }

    public void setAuthenticationTimeout(int authenticationTimeout) {
        this.authenticationTimeout = authenticationTimeout;
    }

    public String getSubdomainHost() {
        return subdomainHost;
    }

    public void setSubdomainHost(String subdomainHost) {
        this.subdomainHost = subdomainHost;
    }
}
