package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class TestParameter {

    public TestParameter(HashMap<book, ArrayList<Integer>> sections) {

        this.sections = sections;
        this.difficulty = difficulty;
        this.closedBook = closedBook;
    }
    public TestParameter (){}

    public enum book {NEPHI1,NEPHI2, JACOB, ENOS, JAROM, OMNI,
        WORDSOFMORMON, MOSIAH, ALMA, HELAMAN, NEPHI3, NEPHI4, MORMON, ETHER, MORONI }
    public enum fileOutputType{XML, WORD, MOODLE}

    private HashMap<book, ArrayList<Integer>> sections;
    private int difficulty;
    private boolean closedBook;






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




}
