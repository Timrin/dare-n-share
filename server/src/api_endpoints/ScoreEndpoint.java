package api_endpoints;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * This class has the endpoint logic for reporting scores
 * It handles requests sent to the /score path
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
		response.setContentType("text/html");

		// Actual logic goes here.
		PrintWriter out = response.getWriter();
		out.println("<h1>" + message + "</h1>");
		out.println("<h3>" + path + "</h3>");
	}

	public void destroy() {
		// do nothing.
	}
}