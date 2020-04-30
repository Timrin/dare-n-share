package api_endpoints;

import database_sockets.UserDB;
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
        serverApiCommunication = new ServerApiCommunication();
    }
    /**
     * This method is invoked when the server receives an http get request.
     *
     * @param request  the http request
     * @param response the response to the request
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        //Parse request
        String pathInfo = request.getPathInfo();
        if (pathInfo.length() >= 2) {
            try {
                String truePath = pathInfo.substring(1);
                response.setContentType("application/json");

                String user = serverApiCommunication.getUser(truePath); //fixme getUser doesn't return ID

                if (user != null) {
                    PrintWriter out = response.getWriter();
                    out.println(user);
                } else {
                    throw new Exception("User not found"); // if the user == null the user doesn't exits
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

        try {
            BufferedReader br = request.getReader();
            System.out.println("doPost1, före getReader");
            StringBuilder stringBuilder = new StringBuilder();
            System.out.println("doPost2, StringBuilder");
            String line = br.readLine();

            while (line != null) {
                stringBuilder.append(line);
                line = br.readLine();
            }

            serverApiCommunication.newUser(stringBuilder.toString());
            System.out.println("server");

            PrintWriter out = response.getWriter();
            response.setStatus(201);
            out.println("{user_id "  + "}"); //todo check if user_id is correct

        } catch (Exception e) {
        }
    }
}