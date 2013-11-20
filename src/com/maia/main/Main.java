package com.maia.main;

import java.io.IOException;

public class Main {
	public static void main(String args[]) {
        Bootstrap bs = new Bootstrap();
        try {
            bs.loadProperties();
            bs.createServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
