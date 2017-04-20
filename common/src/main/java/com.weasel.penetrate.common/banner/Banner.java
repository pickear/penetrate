package com.weasel.penetrate.common.banner;


/**
 * @author dylan
 * @time 2017/4/20
 */
public interface Banner {

    /**
     *
     */
    void printBanner(String name,String version);

    /**
     *
     * @return
     */
    String [] banner();
}
