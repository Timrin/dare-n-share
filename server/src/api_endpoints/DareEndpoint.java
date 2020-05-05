package api_endpoints;

import Converter.ServerApiCommunication;

import java.io.*;
import javax.servlet.http.*;

/**
 * @author Julia, Kamilla and Timothy - XP pair programming
 * @version 1.0
 * This class is an API endpoint for Dares
 * It handles requests sent to the /dare/{id} path
 * @date 08/04-20
 */
public class DareEndpoint extends HttpServlet {

    private ServerApiCommunication serverApiCommunication;

    public DareEndpoint() {
        serverApiCommunication = new ServerApiCommunication();
    }


    /**
     * This method is invoked when the server receives an http get request.
     *
     * @param request  the http request
     * @param response the response to the request
	 *
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        String pathInfo = request.getPathInfo();
        if (pathInfo.length() >= 2) {
            try {
                int truePath = Integer.parseInt(pathInfo.substring(1));

                // Set response content type
                response.setContentType("application/json");

                //Retrieve dare from the db
                String dare = serverApiCommunication.getDare(truePath);

                if (dare != null) {
                    //If the dare exists, write the dare to the response
                    PrintWriter out = response.getWriter();
                    out.println(dare);

                } else {
                    //If the dare doesn't exist
                    throw new Exception("Dare not found");
                }

            } catch (NumberFormatException e) {
                //If the id in path isn't an int respond with 400
                response.setStatus(400);
            } catch (Exception e) {
                //If the id in path doesn't exist in the db respond with 404
                response.setStatus(404);
            }
        } else {
            response.setStatus(400); //if there is no id in the path respond with 400
        }

    }

    /**
     * Invoked when a post request is sent to the /dare api endpoint
     * This method handles the creation of dares
     * @param request  the http request
     * @param response the response to the request
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {
            //Gets reader from request, reads json object line by line and builds one string
            BufferedReader br = request.getReader();
            StringBuilder stringBuilder = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                stringBuilder.append(line);
                line = br.readLine();
            }
            //Sends along complete string from request to make new dare
            int dareId = serverApiCommunication.newDare(stringBuilder.toString());

            //Create response
            PrintWriter out = response.getWriter();
            response.setStatus(201);
            out.println("{dare_id:" + dareId +" }");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}