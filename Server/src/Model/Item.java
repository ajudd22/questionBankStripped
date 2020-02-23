package Model;

public class Item {
    private String itemID;
    private String head;
    private String c;
    private String op1;
    private String op2;
    private String op3;
    private int difficulty;
    private String book;
    private int chapter = -1;

    public Item (String itemID, String head, String c, String op1, String op2, String op3, int difficulty, String book, int chapter)
    {
        this.itemID= itemID;
        this.head = head;
        this.c=c;
        this.op1= op1;
        this.op2= op2;
        this.op3=op3;
        this.difficulty = difficulty;
        this.book = book;
        this.chapter = chapter;
    }
    public Item(){}


    public boolean verifyData() {
        if (itemID == null || book == null || chapter == -1 || c == null || op2 == null || op1 == null || op3 == null) {
            System.out.print("Bad item data");
            return false;
        }
        return true;
    }

    public String getItemID() {
        return itemID;
    }
    public void setItemID(String itemID) {
        this.itemID = itemID;
    }
    public String getHead() {
        return head;
    }
    public void setHead(String head) {
        this.head = head;
    }
    public String getC() {
        return c;
    }
    public void setC(String c) {
        this.c = c;
    }
    public String getOp1() {
        return op1;
    }
    public void setOp1(String op1) {
        this.op1 = op1;
    }
    public String getOp2() {
        return op2;
    }
    public void setOp2(String op2) {
        this.op2 = op2;
    }
    public String getOp3() {
        return op3;
    }
    public void setOp3(String op3) {
        this.op3 = op3;
    }
    public int getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    public String getBook() {
        return book;
    }
    public void setBook(String book) {
        this.book = book;
    }
    public int getChapter() {
        return chapter;
    }
    public void setChapter(int chapter) {
        this.chapter = chapter;
    }







}
