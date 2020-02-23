package Model;

public class Question {

    private String itemID;
    private String head;
    private String c;
    private String op1;
    private String op2;
    private String op3;

    public Question(String itemID, String head, String c, String op1, String op2, String op3) {
        this.itemID = itemID;
        this.head = head;
        this.c = c;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
    }
    public Question(){}


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
    public String getOp1() {
        return op1;
    }
    public void setOp1(String op1) {
        this.op1 = op1;
    }

}
