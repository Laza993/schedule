package Schedule.lectures;

import java.io.IOException;

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
public class ViewLectureServlet extends HttpServlet {

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
			String idS = request.getParameter("viewId");
			if(idS == null || idS == "") {
				response.sendRedirect("AllLecturesServlet");
				System.out.println("bad parameter");
				return;
			}
			int id = Integer.parseInt(idS);
			Lecture lecture = LectureDAO.get((long)id);
			request.setAttribute("lecture", lecture);
			request.getRequestDispatcher("EditLecture.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
