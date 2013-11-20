package com.maia.server;

import com.maia.util.Log;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Phelipe
 * Date: 19/11/13
 * Time: 19:50
 * To change this template use File | Settings | File Templates.
 */
public class CustomHandler implements HttpHandler{
    private Log log = Log.getInstance();
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        log.info("Request");
        String response = null;
        int statusCode = 200;

        try {
            Map<String, Object> params = (Map<String, Object>)httpExchange.getAttribute("parameters");

            if (params.get("request").equals("login")) {

                // The user is logging-in, asking for a session key
                System.out.println("New login request received for the user" +
                        (String)params.get("userid"));

                /*response = SessionSingleton.getInstance().getSessionKey(
                        Integer.parseInt((String)params.get("userid")));*/

                if(response == null) statusCode = 500; // Server error
            }
            else if (params.get("request").equals("score")) {

                // A new store has been received to be stored
                System.out.println("New request received to save the score" +
                        (String)params.get("score"));

                /*int userId = Session.getInstance().validateSessionKey(
                        (String)params.get("sessionkey"));

                if (userId  == -1)
                    statusCode = 401; // Unhautorized user
                else if(ScoreSingleton.getInstance().insertScore(
                        userId, Integer.parseInt((String)params.get("levelid")),
                        Integer.parseInt((String)params.get("score"))) == -1)
                    statusCode = 500; // Server error         */
            }
            else if (params.get("request").equals("highscorelist")) {

                // A list of the best scores has been requested
                System.out.println("Received a new request for the highest scores" +
                        "the level " + (String)params.get("levelid"));

                /*response = ScoreSingleton.getInstance().getHighestScores(
                        Integer.parseInt((String)params.get("levelid")));*/

                // This is a header to permit the download of the csv
                Headers headers = httpExchange.getResponseHeaders();
                headers.add("Content-Type", "text/csv");
                headers.add("Content-Disposition", "attachment;filename=myfilename.csv");
            }
            else {
                response = "Method not implemented";
                System.out.println(response);
                statusCode = 400; // Request type not implemented
            }
        }
        catch (NumberFormatException exception) {
            statusCode = 400;
            response = "Wrong number format";
            System.out.println(response);
        }
        catch (Exception exception) {
            statusCode = 400;
            response = exception.getMessage().toString();
            System.out.println(response);
        }

        // Send the header response
        if (response != null)
            httpExchange.sendResponseHeaders(statusCode, response.length());
        else
            httpExchange.sendResponseHeaders(statusCode, 0);

        // Send the body response
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.toString().getBytes());
        os.close();
    }
}
