package DAO;


import java.sql.*;

public class Database {

    private Connection c;
    PreparedStatement stmt;


    static {
        try {
            //This is how to set up the driver for the database
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void createDBTables() {

        String wholeFile = "CREATE TABLE IF NOT EXISTS authTokenTable(\n" +
                "value text NOT NULL PRIMARY KEY,\n" +
                "netID text NOT NULL);";
        executeDBUpdate(wholeFile);
        closeDBConnection(true);

        wholeFile = "CREATE TABLE IF NOT EXISTS itemTable(\n" +
                "itemID text NOT NULL PRIMARY KEY,\n" +
                "head text NOT NULL,\n" +
                "c text NOT NULL,\n" +
                "op1 text NOT NULL,\n" +
                "op2 text NOT NULL,\n" +
                "op3 text NOT NULL,\n" +
                "difficulty int NOT NULL,\n" +
                "book text NOT NULL,\n" +
                "chapter int NOT NULL\n" +
                ");";
        executeDBUpdate(wholeFile);
        closeDBConnection(true);

        wholeFile = "CREATE TABLE IF NOT EXISTS userTable(\n" +
                "netID text NOT NULL PRIMARY KEY,\n" +
                "password text NOT NULL,\n" +
                "firstName text NOT NULL,\n" +
                "lastName text NOT NULL\n" +
                ");";

        executeDBUpdate(wholeFile);
        closeDBConnection(true);
    }


    void executeDBUpdate(String statement) {
        c = connectToDatabase();
        stmt = null;
        try {
            stmt = c.prepareStatement(statement);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(statement);
            e.printStackTrace();
        }
    }

    ResultSet executeDBQuery(String statement) {
        c = connectToDatabase();
        stmt = null;
        ResultSet rs = null;
        try {
            stmt = c.prepareStatement(statement);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail query!");
        }
        return rs;
    }

    private Connection connectToDatabase() {
        c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Server/src/myBank.db");
            c.setAutoCommit(false);


        } catch (ClassNotFoundException e) {
            System.err.println("Error, couldn't load database driver ");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return c;
    }

    void closeDBConnection(Boolean commit) {
        if (commit) {
            try {
                c.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            if (!c.isClosed()) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearAll()
    {
        String sql = "DROP TABLE authtokentable;";
        executeDBUpdate(sql);
        closeDBConnection(true);
         sql = "DROP TABLE usertable;";
        executeDBUpdate(sql);
        closeDBConnection(true);
         sql = "DROP TABLE itemtable;";
        executeDBUpdate(sql);
        closeDBConnection(true);
        createDBTables();

    }






}
