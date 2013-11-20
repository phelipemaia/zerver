package com.maia.main;

import com.maia.server.CustomFilter;
import com.maia.server.CustomHandler;
import com.maia.util.Log;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.Properties;
import java.util.concurrent.Executors;

public class Bootstrap {
    public int port;
    public Log log = Log.getInstance();

    public Bootstrap() {
        this.port = 8080;
    }

    public void loadProperties() throws IOException {
        Properties prop = new Properties();
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("application.properties");
        prop.load(in);

        if (prop.getProperty("port") != null) {
            this.port = Integer.parseInt(prop.getProperty("port"));
        }
    }

    public void createServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(this.port), 0);

        HttpContext context = server.createContext("/", new CustomHandler());

        context.getFilters().add(new CustomFilter());

        server.setExecutor(Executors.newCachedThreadPool());

        server.start();
        log.info("Server started on port " + this.port);
    }
}
