package Service;

import javax.xml.crypto.Data;

import DAO.Database;
import Response.ClearResponse;


public class ClearService {

    /**
     *
     * *Description: creates lots of DAOs and clears all objects the DAOs access
     *

     * @return Response object
     */
    public ClearResponse execute()
    {
        Database db = new Database();
        db.clearAll();
        ClearResponse response = new ClearResponse();
        response.setResponse("Clear Succeeded!");
        return response;
    }

}
