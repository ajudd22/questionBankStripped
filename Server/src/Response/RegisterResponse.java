package Response;

public class RegisterResponse {
    String message;

    String authToken;
    String netID;





    public void setErrorResponse()
    {
        message = "User not registered because it already exists";
        netID = null;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getResponse() {
        return message;
    }
    public void setResponse(String response) {
        this.message = response;
    }
    public String getNetID() {
        return netID;
    }
    public void setNetID(String netID) {
        this.netID = netID;
    }



    /**

     Errors: Request property missing or has invalid value, Username already taken by another user,
     Internal server error
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
