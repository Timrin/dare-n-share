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

		String pathInfo = request.getPathInfo();
		if (pathInfo.length() >= 2) {
			try {
				int id = Integer.parseInt(pathInfo.substring(1));

				// Set response content type
				response.setContentType("application/json");

				//Retrieve dare from the db
				String dare = dareDB.getDare(id);

				if (dare != null) {
					//If the dare exists, write the dare to the response
					PrintWriter out = response.getWriter();
					out.println(dare);

				} else {

					//If the dare doesn't exist
					throw new Exception("Dare not found");
				}

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

    /**
     * Invoked when a post request is sent to the /dare api endpoint
     * This method handles the creation of dares
     *
     * @param request  the http request
     * @param response the response to the request
     * @throws ServletException
     * @throws IOException
     */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Parse request
		String path = request.getPathInfo();

		try {
			//Read dare from request body
			BufferedReader br = request.getReader();

			StringBuilder stringBuilder = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				stringBuilder.append(line);
				line = br.readLine();
			}

			//Create dare
			int id = dareDB.createDare(stringBuilder.toString());

			//Create response
			PrintWriter out = response.getWriter();
			response.setStatus(201);
			out.println("{dare_id: " + id + "}");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		// do nothing.
	}


}