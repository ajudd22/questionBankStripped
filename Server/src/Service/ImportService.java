package Service;

import DAO.ItemDAO;
import Model.Item;
import Request.ImportRequest;
import Response.ClearResponse;
import Response.ImportResponse;

import java.util.ArrayList;


public class ImportService {

    public ImportResponse execute(ImportRequest r) {
        ImportResponse response = new ImportResponse();
        ItemDAO itemDAO = new ItemDAO();

        Item[] items = r.getItems();

        if (items == null)
        {
            response.setMessage("Bad input.");
            return response;
        }

        ClearService clearService = new ClearService();
        ClearResponse clear = clearService.execute();
        StringBuilder badmessage = new StringBuilder(" ");


        int added = 0;
        for (Item i: items)
        {
            if (!i.verifyData())
            {
                badmessage.append("bad input - question ").append("\n").append(i.getHead());
            }
            else{
                itemDAO.addItem(i);
                added++;
            }

        }

        response.setMessage("Successfully added "+added+"items!");
        response.setBadItems(badmessage.toString());


        return response;
    }

}
