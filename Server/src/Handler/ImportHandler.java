package Handler;

import Request.ImportRequest;
import Response.ImportResponse;
import Service.ImportService;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

//TODO: find a way to parse through bad JSON and import questions with good data and throw out bad ones without the server crashing

public class ImportHandler extends HandlerHelper implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        if (exchange.getRequestMethod().toLowerCase().equals("post")) {// Only allow POST requests for this operation.

            Gson gson = new Gson();

            // Extract the JSON string from the HTTP request body
            InputStream reqBody = exchange.getRequestBody();// Get the request body input stream
            String reqData = readString(reqBody);// Read JSON string from the input stream
            String data = escapePunctuation(reqData);
           // System.out.print(data);
            ImportResponse response = new ImportResponse();
            try {
                ImportRequest request = gson.fromJson(data, ImportRequest.class);
                ImportService importService = new ImportService();
                response = importService.execute(request);


                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                String myResult = gson.toJson(response);

                OutputStream responseBody = exchange.getResponseBody();
                writeString(myResult, responseBody);
                responseBody.close();
            }
            catch(JsonSyntaxException e)
                {
                   e.printStackTrace();
                   response.setMessage("JSON failed: the text you input has errors");
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
                    exchange.getResponseBody().close();
                }

        }
    }

    private String escapePunctuation(String JsonBody)
    {
        StringBuilder data = new StringBuilder();
        for (int i = 0; i <JsonBody.length(); i++)
        {
            data.append(JsonBody.charAt(i));
            char c = JsonBody.charAt(i);
            if (c == '\'')
            {
                data.append('\'');
            }
        }

       // System.out.println(data.toString());
        return data.toString();

    }


    private String reformatJSON(String reqbody) {
        StringBuilder reformattedJSON = new StringBuilder();

        try {
            reformattedJSON.append(reqbody.charAt(0));
            reformattedJSON.append(reqbody.charAt(1));
            reformattedJSON.append(reqbody.charAt(2));
            reformattedJSON.append(reqbody.charAt(3));
            reformattedJSON.append(reqbody.charAt(4));

            int formind = 5;
            for (int i = 5; i < reqbody.length(); i++) {
                reformattedJSON.append(reqbody.charAt(i));
                char a = reqbody.charAt(i - 5);
                char b = reqbody.charAt(i - 4);
                char c = reqbody.charAt(i - 3);
                char d = reqbody.charAt(i - 2);
                char e = reqbody.charAt(i - 1);
                char f = reqbody.charAt(i);
                char[] wordchars = {a, b, c, d, e, f};
                String word = new String(wordchars);
                if (word.equals("\"itemI")) {
                    System.out.println("Added curly brace" + i);
                    reformattedJSON.insert(formind - 7, "\"question\": {\n");
                    formind += 14;

                } else if (word.equals("\"diffi")) {
                    formind += 2;
                    reformattedJSON.deleteCharAt(formind - 13);
                    reformattedJSON.insert(formind - 8, "},\n");//minus 8 to account for the 8 chars of "diffi"

                }
                formind++;
            }
            System.out.println("DONE");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
         return reformattedJSON.toString();

    }

}

