package com.weasel.penetrate.common.helper;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by dylan on 17-3-14.
 */
public final class EnvironmentHelper {

    public static String getEnv(){
        return System.getProperty("spring.profiles.active");
    }

    public static boolean isProd(){
        return StringUtils.equalsIgnoreCase("prod",getEnv());
    }

    public static boolean isDev(){
        return StringUtils.equalsIgnoreCase("dev",getEnv());
    }

    public static boolean idTest(){
        return StringUtils.equalsIgnoreCase("test",getEnv());
    }

    private EnvironmentHelper(){}
}
