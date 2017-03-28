package com.weasel.penetrate.monitor;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.joda.time.DateTime;

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
    private String time = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if(null == o){
            return false;
        }
        if( o == this){
            return true;
        }
        if(!(o instanceof DeviceStatus)){
            return false;
        }
        return new EqualsBuilder().append(this.getNumber(),((DeviceStatus)o).getNumber()).isEquals();
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int number = Integer.valueOf(getNumber());
        return new HashCodeBuilder(number%2==0?number+1:number, PRIME).toHashCode();
    }
}
