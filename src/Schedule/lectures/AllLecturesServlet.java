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
import Schedule.model.Lecture;
import Schedule.model.User;

@SuppressWarnings("serial")
public class AllLecturesServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int theoryCounter = 0;
		int practiceCounter = 0;
		try {
			HttpSession session = request.getSession();
			User loggedUser = (User) session.getAttribute("loggedUser");
			if(loggedUser == null) {
				HashMap<String, Object> answer = new LinkedHashMap<>();
				answer.put("status", "failed");
				answer.put("loggedUser", null);
				String jsonAnswer = new ObjectMapper().writeValueAsString(answer);
				
				response.setContentType("application/json; charset=UTF-8");
				response.getWriter().write(jsonAnswer);
				return;
			}
			List<Lecture> lectures = LectureDAO.getLectures();
			if(lectures == null) {
				HashMap<String, Object> answer = new LinkedHashMap<>();
				answer.put("status", "failed");
				String jsonAnswer = new ObjectMapper().writeValueAsString(answer);
				
				response.setContentType("application/json; charset=UTF-8");
				response.getWriter().write(jsonAnswer);
				return;
				
			}
			for(Lecture lecture : lectures) {
				if(lecture.getTeaching().getId() == 1 ) {
					theoryCounter++;
				}
				if(lecture.getTeaching().getId() == 2) {
					practiceCounter++;
				}
			}
			
			HashMap<String, Object> answer = new LinkedHashMap<>();
			answer.put("status", "success");
			answer.put("lectures", lectures);
			answer.put("theoryCounter", theoryCounter);
			answer.put("practiceCounter", practiceCounter);
			answer.put("loggedUser", loggedUser);
			String jsonAnswer = new ObjectMapper().writeValueAsString(answer);
			
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonAnswer);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
