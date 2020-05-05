package api_endpoints;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class FriendsEndpoint extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String pathInfo = request.getPathInfo();
        if (pathInfo.length() >= 2) {
            try {
                int truePath = Integer.parseInt(pathInfo.substring(1));

                // Set response content type
                response.setContentType("application/json");



                /*if (dare != null) {
                    //If the dare exists, write the dare to the response
                    PrintWriter out = response.getWriter();
                    out.println(dare);*/

              /*  } else {
                    //If the dare doesn't exist
                    throw new Exception("Friends not found");
                }*/

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


            //Create response
            PrintWriter out = response.getWriter();
            response.setStatus(201);
            out.println("{" + " }");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

