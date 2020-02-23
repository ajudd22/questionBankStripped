package Service;

import DAO.AuthTokenDAO;
import Model.AuthToken;
import Model.User;
import DAO.UserDAO;
import Request.RegisterRequest;
import Response.RegisterResponse;

public class RegisterService {



    public RegisterResponse execute(RegisterRequest r){
        RegisterResponse response = new RegisterResponse();
        UserDAO uDAO = new UserDAO();

        if (uDAO.existsUser(r.getnetID())) {
            response.setErrorResponse();
            return response;
        }
        addNewUser(r);

        AuthToken at = logInUser(r.getnetID());
        response.setAuthToken(at.getMyValue());
        response.setNetID(r.getnetID());

        return response;

    }


    private void addNewUser(RegisterRequest r) {
        User u = new User(r.getnetID(), r.getPassword(), r.getFirstName(), r.getLastName());
        UserDAO uDAO = new UserDAO();
        uDAO.addUser(u);
    }
    private AuthToken logInUser(String netID) {
        AuthTokenDAO dao = new AuthTokenDAO();
        AuthToken returnedAuthToken = dao.createAuthToken(netID);
        returnedAuthToken.setNetID(netID);
        dao.addAuthToken(returnedAuthToken);
        return returnedAuthToken;
    }
}
