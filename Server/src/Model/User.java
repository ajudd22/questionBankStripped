package Model;

public class User {

    private String netID;
    private String firstName;
    private String password;
    private String lastName;


    public User(String netID, String password, String firstName, String lastName)
    {
        this.netID = netID;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public User(){}

    public boolean checkifNullVals()
    {
        if (netID == null){return true;}
        if (password == null){return true;}
        if (firstName == null){return true;}
        return lastName == null;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getNetID() {
        return netID;
    }
    public void setNetID(String netID) {
        this.netID = netID;
    }
}
