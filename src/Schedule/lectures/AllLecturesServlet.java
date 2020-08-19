package Schedule.lectures;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Schedule.dao.LectureDAO;
import Schedule.model.Lecture;

@SuppressWarnings("serial")
public class AllLecturesServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
					"</head>\r\n" + 
					"<body>\r\n" + 
					"    <table>\r\n" + 
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
					"            <th>Action</th>\r\n" + 
					"        </tr>\r\n");
					
					for(Lecture lecture : lectures) {
						out.write(
					"        <tr>\r\n" + 
					"            <td> " + lecture.getDay() + "</td>\r\n" + 
					"            <td>" + lecture.getGroup() + "</td>\r\n" + 
					"            <td>" + lecture.getFrom() + "</td>\r\n" + 
					"            <td>" + lecture.getTo() + "</td>\r\n" + 
					"            <td>" + lecture.getClassroom() + "</td>\r\n" + 
					"            <td>" + lecture.getTeaching().getName() + "</td>\r\n" + 
					"            <td>" + lecture.getSubject() + "</td>\r\n" + 
					"            <td>" + lecture.getTeacher() + "</td>\r\n" + 
					"			<td>\r\n" + 
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
					"        </tr>\r\n"
					);
					}
					
					out.write(
					"    </table>\r\n" + 
					"    <a href=\"ViewAddLectureServlet\">Add new lecture</a>\r\n" + 
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
