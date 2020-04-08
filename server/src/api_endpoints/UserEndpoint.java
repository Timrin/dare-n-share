package api_endpoints;

import database_sockets.UserDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class has the endpoint logic for Users
 * It handles requests sent to the /user/{id} path
 */
public class UserEndpoint extends HttpServlet {
	private String user = "  \"uid\": 1,\n" +
			"  \"name\": \"Timothy\",\n" +
			"  \"profile_img\": \"/profile-picture/1\",\n" +
			"  \"dares\": [\n" +
			"    {\n" +
			"      \"id\": \"d1\"\n" +
			"    },\n" +
			"    {\n" +
			"      \"id\": \"d2\"\n" +
			"    },\n" +
			"    {\n" +
			"      \"id\": \"d3\"\n" +
			"    }\n" +
			"  ],\n" +
			"  \"friends\": [\n" +
			"    {\n" +
			"      \"uid\": 2,\n" +
			"      \"name\": \"Steven\",\n" +
			"      \"profile_img\": \"/profile-picture/1\"\n" +
			"    },\n" +
			"    {\n" +
			"      \"uid\": 3,\n" +
			"      \"name\": \"Tor\",\n" +
			"      \"profile_img\": \"/profile-picture/1\"\n" +
			"    }\n" +
			"  ]\n" +
			"}";

	private String message;

	public void init() throws ServletException {
		// Do required initialization
		message = "api_endpoints.UserEndpoint";
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
		//response.setContentType("text/html");
		response.setContentType("application/json");

		// Actual logic goes here.
		PrintWriter out = response.getWriter();
		//out.println("<h1>" + message + "</h1>");
		//out.println("<h3>" + path + "</h3>");

        out.println(UserDB.getUserByID(1)); //fixme


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
		//response.setContentType("text/html");
		response.setContentType("application/json");

		// Actual logic goes here.
		PrintWriter out = response.getWriter();
		//out.println("<h1>" + message + "</h1>");
		//out.println("<h3>" + path + "</h3>");

	//	 out.println(UserDB.getUserByID(1)); //fixme
		//out.println(UserDB.addUser(stringBuilder.toString()));
		out.println("post recieved");

	}

	public void destroy() {
		// do nothing.
	}


}