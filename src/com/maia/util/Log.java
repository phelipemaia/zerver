package com.maia.util;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Phelipe
 * Date: 19/11/13
 * Time: 19:27
 * To change this template use File | Settings | File Templates.
 */
public class Log {

    private static Log instance;

    private Log() {
    }

    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public void info(String text) {
        System.out.println(new Date().toString() + "::ZERVER INFO -- " + text);
    }

    public void debug(String text) {
        System.out.println(new Date().toString() + "::ZERVER DEBUG -- " + text);
    }

    public void error(String text) {
        System.out.println(new Date().toString() + "::ZERVER ERROR -- " + text);
    }
}
