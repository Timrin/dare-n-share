package api_endpoints;

import controller.ServerApiCommunication;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * @author Kamilla, Julia, Steven
 * This is an endpoint for friend requests.
 */

public class FriendsEndpoint extends HttpServlet {

    private ServerApiCommunication serverApiCommunication;

    public FriendsEndpoint() {
        serverApiCommunication = ServerApiCommunication.getInstance();
    }

    /**
     * Invoked when a post request is sent to the /friend api endpoint
     * This method handles friend requests
     *
     * @param request
     * @param response
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        try (BufferedReader br = request.getReader();
             PrintWriter out = response.getWriter()) {

            //Read the json object line by line and builds one string
            StringBuilder stringBuilder = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                stringBuilder.append(line);
                line = br.readLine();
            }int status = serverApiCommunication.newFriend(stringBuilder.toString());

            // Sends complete string to ServerAPICommunication
            if (status==201) {
                response.setStatus(201);
                out.println("{ Friend succesfully added }");
            } else if(status==404) {
                response.setStatus(404);
                out.println("{ Friend not added user friend doesnt even have dare n shar  }");
            }else if (status==409){
                response.setStatus(409);
                out.println("{ Friend not added user friend already exist }");
            }

            //Create response
           // response.setStatus(201);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

