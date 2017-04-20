package com.weasel.penetrate.common.ansi;

/**
 * @author dylan
 * @time 2017/4/20
 */
public interface AnsiElement {

    /**
     * @return the ANSI escape code
     */
    @Override
    String toString();

    /**
     * Internal default {@link AnsiElement} implementation.
     */
    class DefaultAnsiElement implements AnsiElement {

        private final String code;

        DefaultAnsiElement(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return this.code;
        }

    }
}
