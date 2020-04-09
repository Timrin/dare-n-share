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
 *
 * @author Julia and Kamilla - XP pairprogamming
 */
public class UserEndpoint extends HttpServlet {
	/*private String user = "  \"uid\": 1,\n" +
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
			"}";*/

    private String message;
    private UserDB userDB;

	/*public UserEndpoint() {
		userDB = new UserDB();
	}*/

    public void init() throws ServletException {
        // Do required initialization
        message = "api_endpoints.UserEndpoint";
        userDB = new UserDB();
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
        String path = request.getPathInfo();
        if (path.length() >= 2) {
            try {
                int id = Integer.parseInt(path.substring(1));

                response.setContentType("application/json");

                String user = userDB.getUser(id);
                if (user != null) {
                    PrintWriter out = response.getWriter();
                    out.println(user);
                } else {
                    throw new Exception("User not found");
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

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Parse request
		String path = request.getPathInfo();
		try {
			BufferedReader br = request.getReader();
			StringBuilder stringBuilder = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				stringBuilder.append(line);
				line = br.readLine();
			}

			int userId = userDB.addUser(stringBuilder.toString());

			PrintWriter out = response.getWriter();
			response.setStatus(201);
			out.println("{user_id" + userId + "}"); //todo check if user_id is correct
		} catch (Exception e) {
		}


		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader br = request.getReader();
		String line = br.readLine();
		while (line != null) {
			stringBuilder.append(line);
			line = br.readLine();
		}
	}

    public void destroy() {
        // do nothing.
    }


}