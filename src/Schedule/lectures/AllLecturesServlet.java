package Schedule.lectures;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Schedule.dao.LectureDAO;
import Schedule.model.Lecture;
import Schedule.model.User;

@SuppressWarnings("serial")
public class AllLecturesServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int theoryCounter = 0;
		int practiceCounter = 0;
		HttpSession session = request.getSession();
		User loggedUser = (User) session.getAttribute("loggedUser");
		if(loggedUser == null) {
			response.sendRedirect("Login.html");
			return;
		}
		try {
			List<Lecture> lectures = LectureDAO.getLectures();		
			for(Lecture lecture : lectures) {
				if(lecture.getTeaching().getId() == 1 ) {
					theoryCounter++;
				}
				if(lecture.getTeaching().getId() == 2) {
					practiceCounter++;
				}
			}
			request.setAttribute("lectures", lectures);
			request.setAttribute("theoryCounter", theoryCounter);
			request.setAttribute("practiceCounter", practiceCounter);
			request.getRequestDispatcher("AllLectures.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
