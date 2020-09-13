package Schedule.lectures;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

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

		try {
			if(day != null && day.equals("All")) {
				response.sendRedirect("AllLectures.html");
				return;
			}
			Days days = Days.valueOf(day);
			HttpSession session = request.getSession();
			User loggedUser = (User) session.getAttribute("loggedUser");
			
			if(loggedUser == null) {
				HashMap<String, Object> answer  = new LinkedHashMap<>();
				answer.put("status", "failed");
				answer.put("loggedUser", null);
				String jsonAnswer = new ObjectMapper().writeValueAsString(answer);
				System.out.println(jsonAnswer);
				response.setContentType("application/json; charset=UTF-8");
				response.getWriter().write(jsonAnswer);
				
				return;
			}
			List<Lecture> lectures = LectureDAO.getLectures(days);
			
			HashMap<String, Object> answer  = new LinkedHashMap<>();
			answer.put("status", "success");
			answer.put("lectures", lectures);
			answer.put("loggedUser", loggedUser);
			String jsonAnswer = new ObjectMapper().writeValueAsString(answer);
			System.out.println(jsonAnswer);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonAnswer);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
