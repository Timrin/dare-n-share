package api_endpoints;

import database_sockets.DareDB;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * This class has the endpoint logic for Dares
 * It handles requests sent to the /dare/{id} path
 *
 * @author Julia, Kamilla and Timothy - XP pair programming
 */
public class DareEndpoint extends HttpServlet {

    private String message;
    private DareDB dareDB;


	/*
	doGet, if the servlet supports HTTP GET requests
	doPost, for HTTP POST requests
	doPut, for HTTP PUT requests
	doDelete, for HTTP DELETE requests
	init and destroy, to manage resources that are held for the life of the servlet
	getServletInfo, which the servlet uses to provide information about itself
	*/

    public void init() throws ServletException {
        // Do required initialization
        message = "api_endpoints.DareEndpoint";
        dareDB = new DareDB();
    }

    /**
     * This method is invoked when the server receives an http get request.
     *
     * @param request  the http request
     * @param response the response to the request
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Parse request

        String pathInfo = request.getPathInfo();
        if (pathInfo.length() >= 2) {
            try {
                int id = Integer.parseInt(request.getPathInfo().substring(1));


                // Set response content type
                response.setContentType("application/json");

                // Actual logic goes here.
                PrintWriter out = response.getWriter();
                if (dareDB.getDare(id) == null) {
                    throw new Exception(" not found");
                } else {

                    out.println(dareDB.getDare(id)); //fixme
                }
            } catch (NumberFormatException e) {
                response.setStatus(400);
            } catch (Exception e) {
                response.setStatus(404);
            }
        } else {
            response.setStatus(400); //??
        }


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //Parse request
        String path = request.getPathInfo();
        if (path.length() >= 2) {
            try {
                int id = Integer.parseInt(request.getPathInfo().substring(1));

                // Set response content type
                //response.setContentType("application/json");

                // Actual logic goes here.
                //PrintWriter out = response.getWriter();
                String dare = dareDB.getDare(id);

                if (dare != null) {
                    StringBuilder stringBuilder = new StringBuilder();

                    PrintWriter out = response.getWriter();
                    out.println(dare);

                    BufferedReader br = request.getReader();

                    String line = br.readLine();

                    while (line != null) {
                        stringBuilder.append(line);
                        line = br.readLine();
                    }

                    System.out.println(stringBuilder.toString());

                    dareDB.createDare(id, stringBuilder.toString());


                    // Set response content type
                    response.setContentType("application/json");
                    // Actual logic goes here.
                    //PrintWriter out = response.getWriter();

                    out.println("post recieved");

                } else {
                    throw new Exception(" not found");
                    //out.println(dareDB.getDare(id)); //fixme

                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            response.setStatus(400);
        }
    }

    public void destroy() {
        // do nothing.
    }


}