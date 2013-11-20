package com.maia.server;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Phelipe
 * Date: 19/11/13
 * Time: 19:19
 * To change this template use File | Settings | File Templates.
 */
public class CustomFilter extends Filter {
    @Override
    public void doFilter(HttpExchange httpExchange, Chain chain) throws IOException {
        getParameters(httpExchange);
        chain.doFilter(httpExchange);
    }

    @Override
    public String description() {
        return "Treatment of get and post.";
    }

    public void getParameters(HttpExchange exchange) throws UnsupportedEncodingException {
        URI requestedUri = exchange.getRequestURI();
        String uri = requestedUri.getPath();
        Map<String, Object>  parameters = explodeParameters(uri);
        exchange.setAttribute("parameters", parameters);
    }


    private Map<String, Object> explodeParameters(String uri) throws UnsupportedEncodingException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        if (uri != null) {
            String pairs[] = uri.split("[&]");

            for (String pair : pairs) {
                String param[] = pair.split("[=]");

                String key = null;
                String value = null;
                if (param.length > 0) {
                    // Retrieve the key
                    key = URLDecoder.decode(param[0],
                            System.getProperty("file.encoding"));
                }

                if (param.length > 1) {
                    // Retrieve the value
                    value = URLDecoder.decode(param[1],
                            System.getProperty("file.encoding"));
                }

                parameters.put(key.toLowerCase(), value);
            }
        }
        return parameters;
    }
}
