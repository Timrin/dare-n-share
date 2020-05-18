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
 *
 * */

public class FriendsEndpoint extends HttpServlet {

    private ServerApiCommunication serverApiCommunication;

    public FriendsEndpoint() {
        serverApiCommunication = ServerApiCommunication.getInstance();
    }

    /**
     * Invoked when a post request is sent to the /friend api endpoint
     * This method handles friend requests
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
            }

            // Sends complete string to ServerAPICommunication
            serverApiCommunication.newFriend(stringBuilder.toString());

            //Create response
            response.setStatus(201);
            out.println("{ Friend succesfully added }");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

