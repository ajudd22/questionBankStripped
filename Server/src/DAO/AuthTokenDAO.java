package DAO;

import Model.AuthToken;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class AuthTokenDAO extends Database {




    public AuthToken createAuthToken (String NetID)
    {
        String ID = UUID.randomUUID().toString();
        AuthToken newAT = new AuthToken();
        newAT .setMyValue(ID);
        newAT.setNetID(NetID);
        return newAT;
    }

    public boolean existsUser(String authToken)
    {
        String sql = "SELECT netID FROM authTokenTable WHERE value = '" + authToken + "'";
        ResultSet rs = executeDBQuery(sql);
        try {
            if (!rs.next())
            {
                closeDBConnection(false);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeDBConnection(false);
        return true;
    }
    public String getUser (AuthToken at)
    {
        String sql = "SELECT netID FROM authTokenTable WHERE value = '" + at.getMyValue() + "'";
        ResultSet rs = executeDBQuery(sql);
        String username = null;
        try {
            if (!rs.next()){closeDBConnection(false);return null;}
            try {
                username = rs.getString(1);
                closeDBConnection(false);
            } catch (SQLException e) {
                closeDBConnection(false);
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeDBConnection(false);
        return username;
    }

    public void addAuthToken(AuthToken at)
    {
        String sql = "INSERT INTO authTokenTable VALUES ('"+ at.getMyValue()+"', '" + at.getNetID()+"')";
        executeDBUpdate(sql);
        closeDBConnection(true);
    }
}
