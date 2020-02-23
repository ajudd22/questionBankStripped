package Response;

//import Models.Person;

public class LoginResponse {

    String message;
    String netID;

    String authToken;


    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
    public String getAuthToken() {
        return authToken;
    }
    public void setDoesNotExist()
    {
        message = "User does not exist";
    }
    public void setWrongPass(){message = "Wrong password";}
    public String getNetID() {
        return netID;
    }
    public void setNetID(String netID) {
        this.netID = netID;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    /**

     Errors: Request property missing or has invalid value, Internal server error
     Success Response Body:
     {
     "authToken": "cf7a368f", // Non-empty auth token string
     "userName": "susan", // User name passed in with request
     "personID": "39f9fe46" // Non-empty string containing the Person ID of the
     // user’s generated Person object
     }
     Error Response Body:
     {
     “message”: “Description of the error”
     }
     */
}
