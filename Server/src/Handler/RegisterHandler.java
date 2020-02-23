package Handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;

import Request.RegisterRequest;
import Response.RegisterResponse;
import Service.RegisterService;

public class RegisterHandler extends HandlerHelper implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        HandlerHelper helper = new HandlerHelper();

        boolean success = false;

        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {

                // Get the HTTP request headers
                Headers reqHeaders = exchange.getRequestHeaders();

                // Extract the JSON string from the HTTP request body
                InputStream reqBody = exchange.getRequestBody();// Get the request body input stream
                String reqData = readString(reqBody);// Read JSON string from the input stream
                System.out.println(reqData);
                Gson gson = new Gson();
                try {
                    RegisterRequest regUserRequest = gson.fromJson(reqData, RegisterRequest.class);

                    RegisterService regUserService = new RegisterService();


                    RegisterResponse result = regUserService.execute(regUserRequest);
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    gson = new Gson();
                    String myResult = gson.toJson(result);

                    OutputStream responseBody = exchange.getResponseBody();
                    writeString(myResult, responseBody);
                    responseBody.close();

                   // exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    exchange.getResponseBody().close();
                    success = true;

                }catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }

            if (!success) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                exchange.getResponseBody().close();
            }
        } catch (IOException e) {

            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);

            exchange.getResponseBody().close();


            e.printStackTrace();
        }
    }


}




