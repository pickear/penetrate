package com.weasel.penetrate.manager.service;

import java.io.IOException;

/**
 * @author Dylan
 * @date 2017/1/23.
 */
public interface FrpConfigService {

    int reloadConfig(String frpHome) throws IOException, InterruptedException;
}
