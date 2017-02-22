package com.weasel.penetrate.manager.infrastructure.ini;

import org.ini4j.Config;
import org.ini4j.Ini;

/**
 * @author Dylan
 * @date 2017/1/18.
 */
public abstract class IniHandler {

    protected final Config iniCfg = new Config();
    protected final Ini ini = new Ini();
    {
        iniCfg.setMultiSection(true);
        ini.setConfig(iniCfg);
    }
}
