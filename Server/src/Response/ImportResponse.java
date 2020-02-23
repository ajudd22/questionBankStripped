package Response;

public class ImportResponse {
    public ImportResponse(String message, int bankSize, String badItems) {
        this.message = message;
        this.bankSize = bankSize;
        this.badItems = badItems;
    }
    public ImportResponse(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getBankSize() {
        return bankSize;
    }

    public void setBankSize(int bankSize) {
        this.bankSize = bankSize;
    }

    private String message;
    private int bankSize;

    public String getBadItems() {
        return badItems;
    }

    public void setBadItems(String badItems) {
        this.badItems = badItems;
    }

    private String badItems;


}
