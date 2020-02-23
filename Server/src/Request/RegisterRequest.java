package Request;

public class RegisterRequest {
    String netID = " ";
    String password = " ";
    String firstName = " ";
    String lastName = " ";







    public void setNetID(String  netID) {
        this.netID =  netID;
    }
    public String getnetID() {
        return netID;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     *  * URL Path: /user/register
     HTTP Method: POST
     Auth Token Required: No
     Request Body:
     {
     "userName": "susan", // Non-empty string
     4
     "password": "mysecret", // Non-empty string
     "email": "susan@gmail.com", // Non-empty string
     "firstName": "Susan", // Non-empty string
     "lastName": "Ellis", // Non-empty string
     "gender": "f" // “f” or “m”
     }
     */
}
