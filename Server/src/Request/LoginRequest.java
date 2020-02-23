package Request;

public class LoginRequest {
    String netID = " ";
    String password = " ";




    public String getPassword() {
        return password;
    }
    public String getnetID() {
        return netID;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setnetID(String netID) {
        this.netID = netID;
    }
    /** * URL Path: /user/login

     HTTP Method: POST
     Auth Token Required: No
     Request Body:
     {
     "userName": "susan", // Non-empty string
     "password": "mysecret" // Non-empty string
     }
     */
}



