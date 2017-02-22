package com.weasel.penetrate.manager.domain;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by dypan on 2017/2/18.
 */
public class Page<T> {

    private List<T> result = Lists.newArrayList();


    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
