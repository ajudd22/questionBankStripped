package DAO;

import Model.Question;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class QuestionDAO extends Database {

        /**
         *
         * @param itemID
         * creates SQL statement
         * Creates DB connection, adds user
         * @return user
         */
        public Question getQuestion (String itemID)
        {
            String sql = "SELECT * FROM questionTable WHERE itemID = '"+itemID+"';";
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
            Question question = new Question();
            try {
                question.setHead(rs.getString("head"));
                question.setItemID(rs.getString("itemID"));
                question.setC(rs.getString("c"));
                question.setOp1(rs.getString("op1"));
                question.setOp2(rs.getString("op2"));
                question.setOp3(rs.getString("op3"));
                closeDBConnection(false);

            } catch (SQLException e) {
                System.out.println(sql);
                e.printStackTrace();
            }
            closeDBConnection(false);
            return question;
        }

//    public boolean Authenticate (userLoginRequest r)
//    {
//        String sql = "SELECT * FROM userTable WHERE netID = '"+r.getNetID()+"';";
//        ResultSet rs = executeDBQuery(sql);
//        try {
//            if (!rs.next())//user doesn't exist
//            {
//                closeDBConnection(false);
//                return false;
//            }
//        } catch (SQLException e) {
//            closeDBConnection(false);
//            e.printStackTrace();
//        }
//        try {
//            String filedPassword = rs.getString("password");//user is here
//            if (!filedPassword.equals(r.getPassword()))//check if password given is correct
//            {
//                closeDBConnection(false);
//                return false;
//            }
//        } catch (SQLException e) {
//            closeDBConnection(false);
//            e.printStackTrace();
//        }
//        closeDBConnection(false);
//        return true;
//
//
//    }

        public boolean existsItem (String itemID)
        {
            try{
                String sql = "SELECT * FROM userTable WHERE itemID = '"+itemID+"';";
                ResultSet rs = executeDBQuery(sql);
                if (rs.next())//if the item does exist
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
         * @param i
         * creates SQL statement
         * Creates DB connection, adds user
         */
        public void addQuestion(Question i)
        {
            String head = i.getHead().replace("'", "''");
            String c = i.getC().replace("'", "''");
            String op1 = i.getOp1().replace("'", "''");
            String op2 = i.getOp2().replace("'", "''");
            String op3 = i.getOp3().replace("'", "''");

            String sql = "INSERT INTO questionTable VALUES('"+
                    i.getItemID()+"', '"
                    +head+"', '"
                    +c+"', '"
                    +op1+"', '"
                    +op2+"', '"
                    +op3
                    +"')";
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


