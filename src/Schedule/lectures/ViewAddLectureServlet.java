package Schedule.lectures;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Schedule.model.Role;
import Schedule.model.User;

@SuppressWarnings("serial")
public class ViewAddLectureServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loggedUser = (User) session.getAttribute("loggedUser");
		if(loggedUser == null) {
			response.sendRedirect("Login.html");
			return;
		}
		if(!loggedUser.getRole().equals(Role.teacher)) {
			response.sendRedirect("ScheduleServlet");
			return;
		}
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write("<!DOCTYPE html>\r\n" + 
					"<html lang=\"en\">\r\n" + 
					"<head>\r\n" + 
					"    <meta charset=\"UTF-8\">\r\n" + 
					"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
					"    <title>ScheduleAddLecture</title>\r\n" + 
					"    <style>\r\n" + 
					"        tr th {text-align: right;\r\n" + 
					"                width: 150px;}\r\n" + 
					"        tr, td {width: 200px;}\r\n" + 
					"        tr td input, select {box-sizing:content-box;\r\n" + 
					"                            width: 100%;\r\n" + 
					"                            }\r\n" + 
					"        .radio {width: 20px;}   " +
					"        a:link, a:visited {\r\n" + 
					"            background-color: #4085f5;\r\n" + 
					"            color: white;\r\n" + 
					"            padding: 10px 15px;\r\n" + 
					"            text-align: center;\r\n" + 
					"            text-decoration: none;\r\n" + 
					"            display: inline-block;\r\n" + 
					"            border-radius: 10px;\r\n" + 
					"        }\r\n" + 
					"\r\n" + 
					"        a:hover, a:active {\r\n" + 
					"            background-color: blue;\r\n" + 
					"        }" +
					"    </style>\r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					"    <div>\r\n" + 
					"        <form method=\"post\" action=\"AddLectureServlet\">\r\n" + 
					"        <table>\r\n" + 
					"            <caption>Add Lectures</caption>\r\n" + 
					"            <tr>\r\n" + 
					"                <th>Day</th>\r\n" + 
					"                <td>\r\n" + 
					"                    <select name=\"day\" id=\"day\">\r\n" + 
					"                        <option value=\"Monday\">Monday</option>\r\n" + 
					"                        <option value=\"Tuesday\">Tuesday</option>\r\n" + 
					"                        <option value=\"Wednesday\">Wednesday</option>\r\n" + 
					"                        <option value=\"Thursday\">Thursday</option>\r\n" + 
					"                        <option value=\"Friday\">Friday</option>\r\n" + 
					"                        <option value=\"Saturday\">Saturday</option>\r\n" + 
					"                      </select>\r\n" + 
					"                </td>\r\n" + 
					"            </tr>\r\n" + 
					"            <tr>\r\n" + 
					"                <th>Group</th>\r\n" + 
					"                <td>\r\n" + 
					"                    <input type=\"text\" name=\"group\" required maxlength=\"3\">\r\n" + 
					"                </td>\r\n" + 
					"            </tr>\r\n" + 
					"            <tr>\r\n" + 
					"                <th>From</th>\r\n" + 
					"                <td>\r\n" + 
					"                    <input type=\"time\" name=\"from\" required>\r\n" + 
					"                </td>\r\n" + 
					"            </tr>\r\n" + 
					"            <tr>\r\n" + 
					"                <th>To</th>\r\n" + 
					"                <td>\r\n" + 
					"                    <input type=\"time\" name=\"to\" required>\r\n" + 
					"                </td>\r\n" + 
					"            </tr>\r\n" + 
					"            <tr>\r\n" + 
					"                <th>Clasroom</th>\r\n" + 
					"                <td><input type=\"text\" name=\"clasroom\" required></td>\r\n" + 
					"            </tr>\r\n" + 
					"            <tr>\r\n" + 
					"                <th>Teaching</th>\r\n" + 
					"                <td>\r\n" + 
					"                    <table>\r\n" + 
					"                        <tr>\r\n" + 
					"                            <td>\r\n" + 
					"                                <input class=\"radio\"  type=\"radio\" id=\"theory\" name=\"teaching\" value=\"theory\" checked>\r\n" + 
					"                                <label for=\"theory\">Theory</label><br>\r\n" + 
					"                            </td>\r\n" + 
					"                            <td>\r\n" + 
					"                                <input class=\"radio\" type=\"radio\" id=\"practice\" name=\"teaching\" value=\"practice\">\r\n" + 
					"                                <label for=\"practice\">Practice</label><br>\r\n" + 
					"                            </td>\r\n" + 
					"                        </tr>\r\n" + 
					"                    </table>" + 
					"                </td>\r\n" + 
					"            </tr>\r\n" + 
					"            <tr>\r\n" + 
					"                <th>Subject</th>\r\n" + 
					"                <td><input type=\"text\" name=\"subject\" required></td>\r\n" + 
					"            </tr>\r\n" + 
					"            <tr>\r\n" + 
					"                <th>Teacher</th>\r\n" + 
					"                <td><input type=\"text\" name=\"teacher\" required></td>\r\n" + 
					"            </tr>\r\n" + 
					"            <tr>\r\n" + 
					"                <th>&nbsp;</th>\r\n" + 
					"                <td><input type=\"submit\" value=\"add\"></td>\r\n" + 
					"            </tr>\r\n" + 
					"        </table>\r\n" + 
					"        </form>\r\n" + 
					"    </div>\r\n" + 
					"\r\n" + 
					"<a href=\"LogoutServlet\">Logout</a>\r\n" +
					"</body>\r\n" + 
					"</html>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
