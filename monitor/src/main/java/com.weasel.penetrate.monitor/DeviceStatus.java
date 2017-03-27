package com.weasel.penetrate.monitor;

import java.util.Date;

/**
 * Created by dell on 2017/3/27.
 */
public class DeviceStatus {

    private String number;
    private String status;
    private long connects;
    private long flowIn;
    private long flowOut;
    private long totalConnects;
    private Date time;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getConnects() {
        return connects;
    }

    public void setConnects(long connects) {
        this.connects = connects;
    }

    public long getFlowIn() {
        return flowIn;
    }

    public void setFlowIn(long flowIn) {
        this.flowIn = flowIn;
    }

    public long getFlowOut() {
        return flowOut;
    }

    public void setFlowOut(long flowOut) {
        this.flowOut = flowOut;
    }

    public long getTotalConnects() {
        return totalConnects;
    }

    public void setTotalConnects(long totalConnects) {
        this.totalConnects = totalConnects;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
