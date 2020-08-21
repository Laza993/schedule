package Schedule;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Schedule.model.Role;
import Schedule.model.User;


@SuppressWarnings("serial")
public class ScheduleServlet extends HttpServlet {
       
    public ScheduleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loggedUser = (User) session.getAttribute("loggedUser");
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter pw = response.getWriter();
		pw.write("<!DOCTYPE html>\r\n" + 
				"<html lang=\"en\">\r\n" + 
				"<head>\r\n" + 
				"    <meta charset=\"UTF-8\">\r\n" + 
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
				"    <title>Schedule</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"    <h1>Welcome to School Schedule</h1>\r\n");
				if(loggedUser == null) {
					pw.write("<a href=\"Login.html\">Please Sing in</a>\r\n");
				}else if(loggedUser.getRole().equals(Role.student)) {
					pw.write("<a href=\"AllLecturesServlet\">Preview schedule</a>\r\n");
				}
				else {
					pw.write("<a href=\"AllLecturesServlet\">Preview schedule</a>\r\n" + 
				"    <a href=\"ViewAddLectureServlet\">Add a new Lecture</a>\r\n");
				}
				pw.write(
						"</body>\r\n" + 
						"</html>"
						);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
