package Handler;


import com.google.gson.Gson;

import Request.LoginRequest;
import Response.LoginResponse;
import Service.LoginService;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class LoginHandler extends HandlerHelper implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException{


        if (exchange.getRequestMethod().toLowerCase().equals("post")) {

            // Extract the JSON string from the HTTP request body
            InputStream reqBody = exchange.getRequestBody();// Get the request body input stream
            String reqData = readString(reqBody);// Read JSON string from the input stream
            Gson gson = new Gson();
            LoginRequest loginRequest = gson.fromJson(reqData, LoginRequest.class);

            LoginService UserLoginService = new LoginService();
            LoginResponse result = UserLoginService.execute(loginRequest);


            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            gson = new Gson();
            String myResult = gson.toJson(result);

            OutputStream responseBody = exchange.getResponseBody();
            writeString(myResult, responseBody);
            responseBody.close();
        }

    }


}
