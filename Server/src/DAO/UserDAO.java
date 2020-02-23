package DAO;

import Model.User;
import Request.LoginRequest;

import java.sql.ResultSet;
import java.sql.SQLException;

//import Models.User;
//import Request.userLoginRequest;

public class UserDAO extends Database{

    /**
     *
     * @param netID
     * creates SQL statement
     * Creates DB connection, adds user
     * @return user
     */
    public User getUser (String netID)
    {
        String sql = "SELECT * FROM userTable WHERE netID = '"+netID+"';";
        ResultSet rs = executeDBQuery(sql);
        try {
            if (!rs.next())
            {
                closeDBConnection(false);
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        User myUser = new User();
        try {
            myUser.setNetID(rs.getString("netID"));
            myUser.setFirstName(rs.getString("firstName"));
            myUser.setLastName(rs.getString("lastName"));
            myUser.setPassword(rs.getString("password"));
            closeDBConnection(false);

        } catch (SQLException e) {
            System.out.println(sql);
            e.printStackTrace();
        }
        closeDBConnection(false);
        return myUser;
    }
    public boolean Authenticate (LoginRequest r)
    {
        String sql = "SELECT * FROM userTable WHERE netID = '"+r.getnetID()+"';";
        ResultSet rs = executeDBQuery(sql);
        try {
            if (!rs.next())//user doesn't exist
            {
                closeDBConnection(false);
                return false;
            }
        } catch (SQLException e) {
            closeDBConnection(false);
            e.printStackTrace();
        }
        try {
            String filedPassword = rs.getString("password");//user is here
            if (!filedPassword.equals(r.getPassword()))//check if password given is correct
            {
                closeDBConnection(false);
                return false;
            }
        } catch (SQLException e) {
            closeDBConnection(false);
            e.printStackTrace();
        }
        closeDBConnection(false);
        return true;


    }

    public boolean existsUser (String username)
    {
        try{
            String sql = "SELECT * FROM userTable WHERE netID = '"+username+"';";
            ResultSet rs = executeDBQuery(sql);
            if (rs.next())//if the user does exist
            {
                closeDBConnection(false);
                return true;
            }
        } catch (SQLException e) {
            closeDBConnection(false);
            e.printStackTrace();
        }
        closeDBConnection(false);
        return false;
    }

    /**
     *
     * @param u
     * creates SQL statement
     * Creates DB connection, adds user
     */
    public void addUser(User u)
    {
        String sql = "INSERT INTO userTable VALUES('"+u.getNetID()+"', '"+u.getPassword()+"', '"+u.getFirstName()+"', '"
                +u.getLastName()+"')";
        executeDBUpdate(sql);
        closeDBConnection(true);
    }
//    public int addUsersCount(User[] users)
//    {
//        int numAdded = 0;
//        for (User u: users)
//        {
//            if (!existsUser(u.getNetID()))
//            {
//                addUser(u);
//                numAdded++;
//            }
//        }
//        closeDBConnection(false);
//        return numAdded;
//
//    }

}
