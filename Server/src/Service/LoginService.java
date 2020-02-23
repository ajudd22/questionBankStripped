package Service;

import DAO.AuthTokenDAO;
import DAO.ItemDAO;
import DAO.UserDAO;
import Model.AuthToken;
import Model.Item;
import Model.User;
import Request.LoginRequest;
import Response.LoginResponse;
//import Request.userLoginRequest;
//import Results.userLoginResult;


public class LoginService {

    /**
     * *Description: Logs in the user and returns an auth token.
     *
     * @param r
     * @return AuthToken object
     */
    public LoginResponse execute(LoginRequest r) {
        LoginResponse result = new LoginResponse();



        System.out.println("!" +r.getnetID());
        AuthTokenDAO atDAO = new AuthTokenDAO();
        UserDAO uDAO = new UserDAO();

        if (!uDAO.existsUser(r.getnetID())) {
            result.setDoesNotExist();
            return result;
        }
        if (!uDAO.Authenticate(r))
        {
            result.setWrongPass();
            return result;
        }
        User u = uDAO.getUser(r.getnetID());

        AuthToken authToken = atDAO.createAuthToken(r.getnetID());

        atDAO.addAuthToken(authToken);

        result.setAuthToken(authToken.getMyValue());
        //result.setPersonID(u.getPersonID());
        result.setNetID(r.getnetID());

        return result;
    }

}
