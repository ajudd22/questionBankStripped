package DAO;

import Model.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ItemDAO extends Database {

    private String generateItemID()
    {
        return UUID.randomUUID().toString();
    }

    public void addItem(Item i) {
        String newID = generateItemID();
        i.setItemID(newID);

        String sql = "INSERT INTO itemTable VALUES('" +
                i.getItemID() + "', '"
                +i.getHead()+"', '"
                +i.getC()+"', '"
                +i.getOp1()+"', '"
                +i.getOp2()+"', '"
                +i.getOp3()+"', '"
                +i.getDifficulty()+"', '"
                +i.getBook()+"', '"
                +i.getChapter()
                +"')";
        executeDBUpdate(sql);
        closeDBConnection(true);
    }
    public ArrayList<Item> getRandItems (int diff, int amt)
    {
        //TODO: THERE IS A BUG IN THIS FN - result set comes back empty

        ArrayList<Item> Items = new ArrayList<>();
        String sql = "SELECT * FROM itemTable WHERE difficulty = "+diff+";";
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
        try {
           do
           {
               Item i = new Item();
               i.setItemID(rs.getString(1));
               i.setHead(rs.getString(2));
               i.setC(rs.getString(3));
               i.setOp1(rs.getString(4));
               i.setOp2(rs.getString(5));
               i.setOp3(rs.getString(6));
               i.setDifficulty(rs.getInt(7));
               i.setBook(rs.getString(8));
               i.setChapter(rs.getInt(9));
               Items.add(i);
           }while (rs.next());
        } catch (SQLException e) {
            System.out.println(sql);
            e.printStackTrace();
        }
        closeDBConnection(false);

        Collections.shuffle(Items);

        if (Items.size() < amt)
        {
            return Items;
        }
        ArrayList<Item> smallList = new ArrayList<>(Items.subList(0,amt));

        return smallList;//TODO: THIS IS CRASHING THE SERVER

    }


}
