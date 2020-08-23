package Schedule.lectures;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Schedule.dao.LectureDAO;
import Schedule.model.Lecture;
import Schedule.model.Role;
import Schedule.model.User;

@SuppressWarnings("serial")
public class AllLecturesServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int theorys = 0;
		int practices = 0;
		HttpSession session = request.getSession();
		User loggedUser = (User) session.getAttribute("loggedUser");
		if(loggedUser == null) {
			response.sendRedirect("Login.html");
			return;
		}
		try {
			List<Lecture> lectures = LectureDAO.getLectures();
			response.setContentType("text/html; charset=UTF-8"); 
			PrintWriter out = response.getWriter();
			
			out.write("<!DOCTYPE html>\r\n" + 
					"<html lang=\"en\">\r\n" + 
					"<head>\r\n" + 
					"    <meta charset=\"UTF-8\">\r\n" + 
					"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
					"    <title>All Lectures</title>\r\n" + 
					"    <style>\r\n" + 
					"        .mainTable {border-collapse: collapse;\r\n" + 
					"                    border-spacing: 0px;\r\n" + 
					"                  }\r\n" + 
					"        .mainTable tr:nth-child(even){background-color: #f2f2f2;}\r\n" + 
					"        .mainTable tr th {color: aliceblue;\r\n" + 
					"        background-color: blue;\r\n" + 
					"        padding: 10px;}\r\n" + 
					"        .mainTable tr td {\r\n" + 
					"            padding: 5px;\r\n" + 
					"        }" +
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
					"    </style>"+
					"</head>\r\n" + 
					"<body>\r\n" +
					"    <div>\r\n" + 
					"        <table>\r\n" + 
					"            <tr>\r\n" + 
					"                <form action=\"SearchByDayServlet\" method=\"post\">\r\n" + 
					"                <td>\r\n" + 
					"                    <select name=\"day\" id=\"day\" aria-placeholder=\"\">\r\n" + 
					"                        <option value=\"All\">All</option>\r\n" + 
					"                        <option value=\"Monday\">Monday</option>\r\n" + 
					"                        <option value=\"Tuesday\">Tuesday</option>\r\n" + 
					"                        <option value=\"Wednesday\">Wednesday</option>\r\n" + 
					"                        <option value=\"Thursday\">Thursday</option>\r\n" + 
					"                        <option value=\"Friday\">Friday</option>\r\n" + 
					"                        <option value=\"Saturday\">Saturday</option>\r\n" + 
					"                      </select>\r\n" + 
					"                </td>\r\n" + 
					"                <td>\r\n" + 
					"                    <input type=\"submit\" value=\"search\">\r\n" + 
					"                </td>\r\n" + 
					"                </form>\r\n" + 
					"            </tr>\r\n" + 
					"        </table>\r\n" + 
					"    </div>" +
					"    <div>\r\n" +
					"    <table class=\"mainTable\" border=\"1\">\r\n" + 
					"        <caption>Schedule</caption>\r\n" + 
					"        <tr>\r\n" + 
					"            <th>Day</th>\r\n" + 
					"            <th>Group</th>\r\n" + 
					"            <th>From</th>\r\n" + 
					"            <th>To</th>\r\n" + 
					"            <th>Clasroom</th>\r\n" + 
					"            <th>Teaching</th>\r\n" + 
					"            <th>Subject</th>\r\n" + 
					"            <th>Teacher</th>\r\n" + 
					"            <th  colspan=\"2\">Action</th>\r\n" + 
					"        </tr>\r\n");
					
					for(Lecture lecture : lectures) {
						if(lecture.getTeaching().getId() == 1) {
							theorys++;
						}
						if(lecture.getTeaching().getId() == 2) {
							practices++;
						}
						out.write(
					"        <tr>\r\n" + 
					"            <td> " + lecture.getDay() + "</td>\r\n" + 
					"            <td>" + lecture.getGroup() + "</td>\r\n" + 
					"            <td>" + lecture.getFrom() + "</td>\r\n" + 
					"            <td>" + lecture.getTo() + "</td>\r\n" + 
					"            <td>" + lecture.getClassroom() + "</td>\r\n" + 
					"            <td>" + lecture.getTeaching().getName() + "</td>\r\n" + 
					"            <td>" + lecture.getSubject() + "</td>\r\n" + 
					"            <td>" + lecture.getTeacher() + "</td>\r\n"
					);
						if(loggedUser.getRole().equals(Role.teacher)) {
						out.write(	"<td>\r\n" + 
					"                <form method=\"post\" action=\"DeleteLectureServlet\">\r\n" + 
					"                    <input type=\"hidden\" id=\"delId\" name=\"delId\" value=\""+ lecture.getId() +"\">\r\n" + 
					"                    <table>\r\n" + 
					"                        <tr>\r\n" + 
					"                            <td>\r\n" + 
					"                                <input type=\"submit\" value=\"delete\">\r\n" + 
					"                            </td>\r\n" + 
					"                        </tr>\r\n" + 
					"                    </table>\r\n" + 
					"                </form>\r\n" + 
					"			</td>\r\n" + 
					"			<td>\r\n" + 
					"                <form method=\"get\" action=\"ViewLectureServlet\">\r\n" + 
					"                    <input type=\"hidden\" id=\"viewId\" name=\"viewId\" value=\""+ lecture.getId() +"\">\r\n" + 
					"                    <table>\r\n" + 
					"                        <tr>\r\n" + 
					"                            <td>\r\n" + 
					"                                <input type=\"submit\" value=\"edit\">\r\n" + 
					"                            </td>\r\n" + 
					"                        </tr>\r\n" + 
					"                    </table>\r\n" + 
					"                </form>\r\n" + 
					"			</td>\r\n" +
					"        </tr>\r\n");
						}
						}
					out.write(
					"    </table>\r\n"+
					"    </div>" +
					"    <div>\r\n" + 
					"        <table>\r\n" + 
					"            <tr>\r\n" + 
					"                <th>number of theory classes</th>\r\n" + 
					"                <td>"+ theorys +"</td>\r\n" + 
					"            </tr>\r\n" + 
					"            <tr>\r\n" + 
					"                <th>number of practice classes</th>\r\n" + 
					"                <td>"+ practices +"</td>\r\n" + 
					"            </tr>\r\n" + 
					"        </table>\r\n" + 
					"    </div>");
					if(loggedUser.getRole().equals(Role.teacher)) {
						out.write("<a href=\"ViewAddLectureServlet\">Add new lecture</a>\r\n");
					}
					out.write(
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
