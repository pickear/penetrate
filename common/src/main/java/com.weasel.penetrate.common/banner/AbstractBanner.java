package com.weasel.penetrate.common.banner;

import com.weasel.penetrate.common.ansi.AnsiColor;
import com.weasel.penetrate.common.ansi.AnsiOutput;
import com.weasel.penetrate.common.ansi.AnsiStyle;

/**
 * @author dylan
 * @time 2017/4/20
 */
public abstract class AbstractBanner implements Banner{

    protected static final int STRAP_LINE_SIZE = 42;

    @Override
    public void printBanner(String name,String version) {

        for (String line : banner()) {
            System.out.println(line);
        }
        version = (version == null ? "" : " (v" + version + ")");
        String padding = "";
        while (padding.length() < STRAP_LINE_SIZE
                - (version.length() + name.length())) {
            padding += " ";
        }

        System.out.println(AnsiOutput.toString(AnsiColor.GREEN, name,
                AnsiColor.DEFAULT, padding, AnsiStyle.FAINT, version));
        System.out.println();
    }
}
