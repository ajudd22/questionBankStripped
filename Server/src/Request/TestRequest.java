package Request;

import Model.TestParameter;

import java.util.ArrayList;
import java.util.HashMap;

public class TestRequest {

    private ArrayList<TestParameter> sections;
    private int difficulty;
    private boolean closedBook;
    private int length;
    private int assortment;//how diverse the difficulty is? strictly at the level or a good assortment.

    public ArrayList<TestParameter> getSections() {
        return sections;
    }
    public void setSections(ArrayList<TestParameter> sections) {
        this.sections = sections;
    }
    public int getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    public boolean isClosedBook() {
        return closedBook;
    }
    public void setClosedBook(boolean closedBook) {
        this.closedBook = closedBook;
    }
    public int getLength() {

        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public int getAssortment() {
        return assortment;
    }
    public void setAssortment(int assortment) {
        this.assortment = assortment;
    }
}
