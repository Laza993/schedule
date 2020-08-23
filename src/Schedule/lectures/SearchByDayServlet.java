package Schedule.lectures;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Schedule.dao.LectureDAO;
import Schedule.model.Days;
import Schedule.model.Lecture;
import Schedule.model.User;

@SuppressWarnings("serial")
public class SearchByDayServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String day = request.getParameter("day");
		if(day.equals("All")) {
			response.sendRedirect("AllLecturesServlet");
			return;
		}
		Days days = Days.valueOf(day);
		HttpSession session = request.getSession();
		User loggedUser = (User) session.getAttribute("loggedUser");
		if(loggedUser == null) {
			response.sendRedirect("Login.html");
			return;
		}
		try {
			List<Lecture> lectures = LectureDAO.getLectures(days);
			request.setAttribute("lectures", lectures);
			request.getRequestDispatcher("SearchLectures.jsp").forward(request, response);;
					
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
