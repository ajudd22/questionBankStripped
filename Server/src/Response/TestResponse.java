package Response;

import Model.Item;
import Model.Question;

import java.util.ArrayList;

public class TestResponse {
    ArrayList<Item> Test;

    //TODO: Make this response contain a document object that is passed back alongside the arraylist of items.
    public TestResponse(ArrayList<Item> items, String message) {
        this.Test = items;
        this.message = message;
    }
    public TestResponse(){}
    public void addItems(Item i)
    {
        Test.add(i);
    }

    public ArrayList<Item> getQuestions() {
        return Test;
    }

    public void setQuestions(ArrayList<Item> items) {
        this.Test = items;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    String message;



}
