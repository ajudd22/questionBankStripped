
import DAO.Database;
import Handler.*;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Logger;

public class Server{
    private static final int maxConnections = 12;

    private static Logger logger;
    private HttpServer server;
    private Database database = new Database();

    public static final void main(String[] args) {
        String portNumber = args[0];
        new Server().run(portNumber);
    }

    private void run(String portNumber) {


        System.out.println("Initializing HTTP src.Server");

        try {
            server = HttpServer.create(
                    new InetSocketAddress(Integer.parseInt(portNumber)),// Create a new HttpServer object.
                    maxConnections);
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }
        server.setExecutor(null);// This line is necessary, but its function is unimportant for our purposes.
        // The HttpServer class listens for incoming HTTP requests.  When one
        // is received, it looks at the URL path inside the HTTP request, and
        // forwards the request to the handler for that URL path.

        System.out.println("Creating contexts");
        server.createContext("/", new DefaultHandler());
      //  server.createContext("/user/register", new RegisterHandler());
      //  server.createContext("/user/login", new LoginHandler());
        server.createContext("/createTest", new TestRequestHandler());
    //    server.createContext("/importer", new ImportHandler());

        database.createDBTables();
        System.out.println("Starting server");
        server.start();
        // Tells the HttpServer to start accepting incoming client connections.


        System.out.println("src.Server started");
    }
}
