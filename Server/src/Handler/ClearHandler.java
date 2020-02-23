package Handler;



import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import Response.ClearResponse;
import Service.ClearService;

public class ClearHandler extends HandlerHelper implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {// Only allow POST requests for this operation.
                ClearService clService = new ClearService();
                ClearResponse result = clService.execute();
                if (result.getResponse().equals("Clear Succeeded!")) {

                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);//set headers

                    //convert result object to Json, send with the exchange object to output
                    Gson gson = new Gson();
                    String myResult = gson.toJson(result);//make a string with special json format

                    OutputStream responseBody = exchange.getResponseBody();
                    writeString(myResult, responseBody);//send json and exchange objects to output
                    responseBody.close();

                    //find httphelper code

                }
            }
        } catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();

        }
    }


}

