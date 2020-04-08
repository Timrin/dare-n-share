package api_endpoints;

import database_sockets.DareDB;
import database_sockets.DareSocket;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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


        // Set response content type
        response.setContentType("application/json");
        response.setContentType("text/html");

        // Actual logic goes here.
        PrintWriter out = response.getWriter();


        for(int i = 0; i<10;i++) {

            out.println(dareDB.getDare(i)); //fixme
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //Parse request
        String path = request.getPathInfo();

        StringBuilder stringBuilder = new StringBuilder();

        BufferedReader br = request.getReader();

        String line = br.readLine();

        while (line != null) {
            stringBuilder.append(line);
            line = br.readLine();
        }

        System.out.println(stringBuilder.toString());

        dareDB.recieveDareFromClient(stringBuilder.toString());


        // Set response content type
        response.setContentType("application/json");
        // Actual logic goes here.
        PrintWriter out = response.getWriter();

        out.println("post recieved");

    }


    public void destroy() {
        // do nothing.
    }


}