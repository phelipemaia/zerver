package com.maia.server;

/**
 * Created with IntelliJ IDEA.
 * User: Phelipe
 * Date: 19/11/13
 * Time: 20:03
 * To change this template use File | Settings | File Templates.
 */
public class Session {
    private static Session instance;

    private Session() {

    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }
}
