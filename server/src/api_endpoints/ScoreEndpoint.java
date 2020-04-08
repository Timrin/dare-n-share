package api_endpoints;

import database_sockets.ScoreDB;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * This class has the endpoint logic for reporting scores
 * It handles requests sent to the /score path
 * @ author Julia and Kamilla - XP pair programming
 */
public class ScoreEndpoint extends HttpServlet {

	private String message;

	public void init() throws ServletException {
		// Do required initialization
		message = "api_endpoints.ScoreEndpoint";
	}

	/**
	 * This method is invoked when the server receives an http get request.
	 *
	 * @param request the http request
	 * @param response the response to the request
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//Parse request
		String path = request.getPathInfo();

		// Set response content type
		// json obj
		// in the teams wiki
		//response.setContentType("text/html");
		response.setContentType("application/json");

		// Actual logic goes here.
		PrintWriter out = response.getWriter();
		//out.println("<h1>" + message + "</h1>");
		//out.println("<h3>" + path + "</h3>");
		out.println(ScoreDB.getScorebyUserID(1));
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		//Parse request
		String path = request.getPathInfo();

		StringBuilder stringBuilder = new StringBuilder();

		BufferedReader br = request.getReader();

		String line = br.readLine();

		while (line!= null){
			stringBuilder.append(line);
			line = br.readLine();
		}

		System.out.println(stringBuilder.toString());
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