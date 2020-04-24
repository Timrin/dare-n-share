package api_endpoints;

import Converter.ServerApiCommunication;

import java.io.*;
import javax.servlet.http.*;

/**
 * This class has the endpoint logic for reporting scores
 * It handles requests sent to the /score path
 *
 * @author Kamilla
 * @date 09/04-20
 * @version 1.0
 */
public class ScoreEndpoint extends HttpServlet {

    private ServerApiCommunication serverApiCommunication;

    public ScoreEndpoint() {
        serverApiCommunication = new ServerApiCommunication();
    }


    /**
     * Invoked when a post request is sent to the /score api endpoint
     * This method handles added points to score
     * @param request  the http request
     * @param response the response to the request
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {
            BufferedReader br = request.getReader();

            StringBuilder stringBuilder = new StringBuilder();
            String line = br.readLine();


            while (line != null) {
                stringBuilder.append(line);
                line = br.readLine();

            }
            String newScore = stringBuilder.toString();
            //scoreDB.setScoreToUser(newScore); // this adds score to a arraylist.

            serverApiCommunication.newScore(stringBuilder.toString());

            PrintWriter out = response.getWriter();

            response.setStatus(201);

            out.println("{Score recieved YAY: " + newScore + " }");


        } catch (Exception e) {

        }

    }

}