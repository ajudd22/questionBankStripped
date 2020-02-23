package Handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.file.*;




public class DefaultHandler implements HttpHandler {


    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String UrlPath = " ";
        if (exchange.getRequestMethod().toLowerCase().equals("get"))//only run if get method
        {
            UrlPath = exchange.getRequestURI().getPath();//grab file path
        }
        if (UrlPath.length() == 0 || UrlPath.equals("/")) {
            UrlPath = "/index.html";
        }
        String strFilePath = "Server/web" + UrlPath;
        System.out.println("URL Path2: " + strFilePath);


        File file = new File(strFilePath);
        if (file.exists() && file.canRead()) {

            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);//sends success
            Path filePath = FileSystems.getDefault().getPath(strFilePath);//turns the string into a path
            Files.copy(filePath, exchange.getResponseBody());//puts the file path into the response body
            //printHeaders(exchange.getResponseHeaders());

        } else if (!file.exists()) {
            System.out.println("FILE AINT EXIST");
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
        }
        exchange.getResponseBody().close();
    }
}
