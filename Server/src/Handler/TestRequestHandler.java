package Handler;

import Request.TestRequest;
import Response.TestResponse;
import Service.TestService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class TestRequestHandler extends HandlerHelper implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("/createTest");
        if (exchange.getRequestMethod().toLowerCase().equals("post")){
            System.out.println("get request");

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
//            gson = new GsonBuilder()
//                    .disableHtmlEscaping()
//                    .create();

            String myResult = gson.toJson(result);

            OutputStream responseBody = exchange.getResponseBody();
            writeString(myResult, responseBody);
            responseBody.close();
        }

     }

    // private Document createXML (TestResponse response)
    // {
    //
    //     String headtext = "Just before the Nephites and Lamanites " +
    //             "become separate peoples, Laman and Lemuel murmur " +
    //             "against Nephi because of what?";
    //     String c = "The death of their father";
    //     String o1 = "The lack of food";
    //     String o2 = "The toiling of their women";
    //     String o3 = "Nephi's status as their teacher";
    //     //Create XML file
    //     DocumentBuilderFactory dbFactory =
    //             DocumentBuilderFactory.newInstance();
    //     DocumentBuilder dBuilder = null;
    //     try {
    //         dBuilder = dbFactory.newDocumentBuilder();
    //     } catch (ParserConfigurationException e) {
    //         e.printStackTrace();
    //     }
    //     Document doc = dBuilder.newDocument();
    //
    //     // root element
    //     Element rootElement = doc.createElement("quiz");
    //     doc.appendChild(rootElement);
    //
    //     //Item element
    //     Element item = doc.createElement("question");
    //     rootElement.appendChild(item);
    //
    //     //Item attribute = multichoice
    //     Attr attr = doc.createAttribute("type");
    //     attr.setValue("multichoice");
    //     item.setAttributeNode(attr);
    //
    //     //Head Element: "Question text"
    //     Element head = doc.createElement("questiontext");
    //     Attr attr1 = doc.createAttribute("format");
    //     attr1.setValue("html");
    //     head.setAttributeNode(attr1);
    //     item.appendChild(head);
    //
    //     head.appendChild(doc.createTextNode("<![CDATA["+headtext+"]]>" ));
    //
    //    Element defaultgrade = doc.createElement("defaultgrade");
    //    defaultgrade.appendChild(doc.createTextNode("1"));
    //    item.appendChild(defaultgrade);
    //
    //     Element single = doc.createElement("single");
    //     defaultgrade.appendChild(doc.createTextNode("1"));
    //     item.appendChild(single);
    //     Element shuffleanswers = doc.createElement("shuffleanswers");
    //     defaultgrade.appendChild(doc.createTextNode("true"));
    //     item.appendChild(shuffleanswers);
    //     Element answernumbering = doc.createElement("answernumbering");
    //     defaultgrade.appendChild(doc.createTextNode("abc"));
    //     item.appendChild(answernumbering);
    //
    //
    //     Element answer = doc.createElement("answer");
    //     Attr attr2 = doc.createAttribute("fraction");
    //     attr2.setValue("0");
    //     Attr attr3 = doc.createAttribute("format");
    //     attr3.setValue("html");
    //
    //
    //
    //
    //
    //
    //
    //
    // }
}
