package api_endpoints;

import Converter.ServerApiCommunication;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * This class has the endpoint logic for Users
 * It handles requests sent to the /user/{id} path
 *
 * @author Julia and Kamilla - XP pairprogamming
 * @date 08/04-20
 * @version 1.0
 */
public class UserEndpoint extends HttpServlet {

    private ServerApiCommunication serverApiCommunication;

    public UserEndpoint() {
        serverApiCommunication = ServerApiCommunication.getInstance();
        System.out.println("New UserEndpoint created");
    }
    /**
     * This method is invoked when the server receives an http get request.
     * It gets a user ID from the url path, and gets a user from the database based on that ID.
     *
     * @param request  the http request
     * @param response the response to the request
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        //Gets the PathInfo which shall include the user ID
        String pathInfo = request.getPathInfo();

        //Checks that path is not only '/'
        if (pathInfo.length() >= 2) {
            try {

                //PathInfo includes '/' at index 0, which is excluded here
                String userID = pathInfo.substring(1);
                response.setContentType("application/json");

                //Sends request through backend to server and receives String containing user data
                String user = serverApiCommunication.getUser(userID);

                //Makes sure user != null before sending user as response
                if (user != null) {
                    try(PrintWriter out = response.getWriter();) {
                        out.println(user);
                        response.setStatus(200);
                    } catch (Exception e) {
                        response.setStatus(500); //Something went wrong when writing to the out stream
                    }
                } else {
                    //If user == null, exception is thrown TODO: set response so that client recognizes this error
                    response.setStatus(404);
                    throw new Exception("User not found");
                }
            } catch (NumberFormatException e) {
                response.setStatus(400);
            } catch (Exception e) {
                response.setStatus(404);
            }
        } else {
            response.setStatus(400);
        }
    }
    /**
     * Invoked when a post request is sent to the /user api endpoint
     * This method handles the creation of users
     * @param request  the http request
     * @param response the response to the request
     */
@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        try (BufferedReader br = request.getReader();
             PrintWriter out = response.getWriter()) {

            System.out.println("UserEndpoint:doPost, reader accepted");

            StringBuilder stringBuilder = new StringBuilder();

            //Reads first line of the request body
            String line = br.readLine();
            System.out.println("UserEndpoint:doPost, first line read:" + line);

            //Reads through body of request line by line, appending stringBuilder until line == null
            while (line != null) {
                stringBuilder.append(line);
                line = br.readLine();
            }

            //Sends complete string to serverApiCommunication
            serverApiCommunication.newUser(stringBuilder.toString());
            System.out.println("UserEndpoint:doPost, string sent to serverApiCommunication");

            //Sets response status to 201 successful
            response.setStatus(201);
            out.println("{user_id "  + "}"); //Fixme: This helps the developer team, but has no real function. Remove before launch

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}