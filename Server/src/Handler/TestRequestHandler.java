package Handler;

import Request.TestRequest;
import Response.TestResponse;
import Service.TestService;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class TestRequestHandler extends HandlerHelper implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("/createTest handler");
        if (exchange.getRequestMethod().toLowerCase().equals("post")){
            System.out.println("request - POST confirmed");

            InputStream reqBody = exchange.getRequestBody();
            String reqData = readString(reqBody);

            Gson gson = new Gson();
            TestRequest testRequest = new TestRequest();
            System.out.println(reqData);

            try{
            testRequest = gson.fromJson(reqData, TestRequest.class);}
            catch(Exception e){
                e.printStackTrace();
            }
            System.out.println("gson done");

            //Get the test
            TestService testService = new TestService();
            TestResponse result = testService.execute(testRequest);


            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

            String myResult = gson.toJson(result);

            OutputStream responseBody = exchange.getResponseBody();
            writeString(myResult, responseBody);
            responseBody.close();
        }
        else{
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        }

     }


}
